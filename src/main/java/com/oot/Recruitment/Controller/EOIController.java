package com.oot.Recruitment.Controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oot.Recruitment.Models.Applicant;
import com.oot.Recruitment.Models.Apprenticeship;
import com.oot.Recruitment.Models.EOI;
import com.oot.Recruitment.Models.Education;
import com.oot.Recruitment.Models.Employment;
import com.oot.Recruitment.Models.PermanentPosition;
import com.oot.Recruitment.Models.PermanentReturnPosition;
import com.oot.Recruitment.Repository.EOIRepository;

import com.oot.Recruitment.Utils.*;

@RestController
@RequestMapping(value = "/EOI")
public class EOIController {
    private final EOIRepository repository;

    EOIController(EOIRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping
	public ResponseEntity<Collection<EOI>> getEOIs() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EOI> getEOI(@PathVariable long id) {
		EOI eoi = repository.findById(id).get();

		if (eoi != null) {
			return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/{id}/Applicant")
	public ResponseEntity<Applicant> getApplicant(@PathVariable long id) {
		Applicant applicant = repository.findById(id).get().getApplicant();

		if (applicant != null) {
			return new ResponseEntity<>(repository.findById(id).get().getApplicant(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/{id}/Educations")
	public ResponseEntity<Collection<Education>> getEducations(@PathVariable long id) {
		Collection<Education> educations = repository.findById(id).get().getEducation();

		if (educations != null) {
			return new ResponseEntity<>(repository.findById(id).get().getEducation(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/{id}/Employments")
	public ResponseEntity<Collection<Employment>> getEmployments(@PathVariable long id) {
		Collection<Employment> employments = repository.findById(id).get().getEmployment();

		if (employments != null) {
			return new ResponseEntity<>(repository.findById(id).get().getEmployment(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}	

	@GetMapping(value = "/{id}/ApprenticeshipEOI")
	public ResponseEntity<Apprenticeship> getApprenticeshipEOI(@PathVariable long id) {
		Apprenticeship apprenticeship = repository.findById(id).get().getApprenticeship();

		if (apprenticeship != null) {
			return new ResponseEntity<>(repository.findById(id).get().getApprenticeship(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/{id}/PermanentPositionEOI")
	public ResponseEntity<PermanentPosition> getPermanentPositionEOI(@PathVariable long id) {
		PermanentPosition permanent_pos = repository.findById(id).get().getPermanent_pos();

		if (permanent_pos != null) {
			return new ResponseEntity<>(repository.findById(id).get().getPermanent_pos(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/{id}/PermanentReturnPositionEOI")
	public ResponseEntity<PermanentReturnPosition> getPermanentReturnPositionEOI(@PathVariable long id) {
		PermanentReturnPosition permanent_return_pos = repository.findById(id).get().getPermanent_return_pos();

		if (permanent_return_pos != null) {
			return new ResponseEntity<>(repository.findById(id).get().getPermanent_return_pos(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> addEOI(@RequestBody EOI submitted_eoi) {
		EOI eoi = null;
		eoi=createupdateEOI(eoi, submitted_eoi);
		return new ResponseEntity<>(repository.save(eoi), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateEOI(@PathVariable long id,@RequestBody EOI submitted_eoi) {
		if(repository.findById(id).isPresent()) {
			EOI eoi = repository.findById(id).get();
			eoi=createupdateEOI(eoi, submitted_eoi);
			return new ResponseEntity<>(repository.save(eoi), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEOIbyID(@PathVariable long id) {
		if(repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	private EOI createupdateEOI(EOI update_eoi,EOI source_eoi) {
		source_eoi.getApplicant().setAgeRange(Utils.calculateAgeRange(source_eoi.getApplicant()));
		
		if(update_eoi==null) {
			update_eoi = new EOI();
		}
		else {
			long existing_applicant_id = update_eoi.getApplicant().getId();
			source_eoi.getApplicant().setId(existing_applicant_id);
		}
		update_eoi.setApplicant(source_eoi.getApplicant());
		
		update_eoi.getEducation().clear();
		update_eoi.getEducation().addAll(source_eoi.getEducation());
		
		update_eoi.getEmployment().clear();
		update_eoi.getEmployment().addAll(source_eoi.getEmployment());

		update_eoi.setEnglish_proficiency_level(source_eoi.getEnglish_proficiency_level());
		
		if(source_eoi.isApprenticeship_toggle()) {
			update_eoi.setApprenticeship_toggle(source_eoi.isApprenticeship_toggle());
			Apprenticeship apprenticeship = update_eoi.getApprenticeship();
			if(apprenticeship==null) {
				apprenticeship = new Apprenticeship();
			}
			apprenticeship.setTotal_points(Utils.calculateTotalPointsApprenticeship(source_eoi));
			update_eoi.setApprenticeship(apprenticeship);
		}
		else {
			update_eoi.setApprenticeship_toggle(source_eoi.isApprenticeship_toggle());
			update_eoi.setApprenticeship(null);
		}
		
		if(source_eoi.isPermanent_pos_toggle()) {
			update_eoi.setPermanent_pos_toggle(source_eoi.isPermanent_pos_toggle());
			PermanentPosition permanent_pos = update_eoi.getPermanent_pos();
			if(permanent_pos==null) {
				permanent_pos = new PermanentPosition();
			}
			permanent_pos.setTotal_points(Utils.calculateTotalPointsPermanentPosition(source_eoi));
			update_eoi.setPermanent_pos(permanent_pos);
		}
		else {
			update_eoi.setPermanent_pos_toggle(source_eoi.isPermanent_pos_toggle());
			update_eoi.setPermanent_pos(null);
		}
		
		if(source_eoi.isPermanent_return_pos_toggle()) {
			update_eoi.setPermanent_return_pos_toggle(source_eoi.isPermanent_return_pos_toggle());
			PermanentReturnPosition permanent_return_pos = update_eoi.getPermanent_return_pos();
			if(permanent_return_pos==null) {
				permanent_return_pos = new PermanentReturnPosition();
			}
			permanent_return_pos.setTotal_points(Utils.calculateTotalPointsPermanentReturnPosition(source_eoi));
			update_eoi.setPermanent_return_pos(permanent_return_pos);
		}
		else {
			update_eoi.setPermanent_return_pos_toggle(source_eoi.isPermanent_return_pos_toggle());
			update_eoi.setPermanent_return_pos(null);
		}
		
		update_eoi.setStatus(source_eoi.getStatus());

		return update_eoi;
	}

}
