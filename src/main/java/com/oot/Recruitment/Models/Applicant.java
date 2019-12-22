package com.oot.Recruitment.Models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.oot.Recruitment.Enumerated.AgeRange;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
public class Applicant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Date birthdate;
    @Enumerated(EnumType.STRING)
    private AgeRange ageRange;
}
