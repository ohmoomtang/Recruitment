package com.oot.Recruitment.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oot.Recruitment.Repository.EOIRepository;

@RestController
@RequestMapping(value = "/eoi")
public class EOIController {
    private final EOIRepository repository;

    EOIController(EOIRepository repository) {
        this.repository = repository;
    }

}
