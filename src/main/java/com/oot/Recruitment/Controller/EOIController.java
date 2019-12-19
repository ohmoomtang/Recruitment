package com.oot.Recruitment.Controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		EOI eoi = new EOI();
		
		eoi.setApplicant(submitted_eoi.getApplicant());
		eoi.setEducation(submitted_eoi.getEducation());
		eoi.setEmployment(submitted_eoi.getEmployment());
		eoi.setEnglish_proficiency_level(submitted_eoi.getEnglish_proficiency_level());
		
		if(submitted_eoi.isApprenticeship_toggle()) {
			eoi.setApprenticeship_toggle(submitted_eoi.isApprenticeship_toggle());
			eoi.setApprenticeship(submitted_eoi.getApprenticeship());
		}
		else {
			eoi.setApprenticeship_toggle(submitted_eoi.isApprenticeship_toggle());
		}
		
		if(submitted_eoi.isPermanent_pos_toggle()) {
			eoi.setPermanent_pos_toggle(submitted_eoi.isPermanent_pos_toggle());
			eoi.setPermanent_pos(submitted_eoi.getPermanent_pos());
		}
		else {
			eoi.setPermanent_pos_toggle(submitted_eoi.isPermanent_pos_toggle());
		}
		
		if(submitted_eoi.isPermanent_return_pos_toggle()) {
			eoi.setPermanent_return_pos_toggle(submitted_eoi.isPermanent_return_pos_toggle());
			eoi.setPermanent_return_pos(submitted_eoi.getPermanent_return_pos());
		}
		else {
			eoi.setPermanent_return_pos_toggle(submitted_eoi.isPermanent_return_pos_toggle());
		}
		
		eoi.setStatus(submitted_eoi.getStatus());
		
		return new ResponseEntity<>(repository.save(eoi), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEOIbyID(@PathVariable long id) {
		repository.deleteById(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
