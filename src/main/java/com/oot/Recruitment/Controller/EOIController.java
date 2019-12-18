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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oot.Recruitment.Models.Applicant;
import com.oot.Recruitment.Models.EOI;
import com.oot.Recruitment.Repository.EOIRepository;

@RestController
@RequestMapping(value = "/eoi")
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
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/{id}/applicant")
	public ResponseEntity<Applicant> getApplicantByEOIID(@PathVariable long id) {
		Applicant applicant = repository.findById(id).get().getApplicant();

		if (applicant != null) {
			return new ResponseEntity<>(repository.findById(id).get().getApplicant(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> addEOI(@RequestBody EOI eoi) {
		return new ResponseEntity<>(repository.save(eoi), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEOIbyID(@PathVariable long id) {
		repository.deleteById(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
