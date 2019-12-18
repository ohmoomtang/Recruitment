package com.oot.Recruitment.Models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Applicant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Date birthdate;
    private int ageRange;
}
