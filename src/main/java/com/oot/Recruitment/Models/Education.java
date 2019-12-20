package com.oot.Recruitment.Models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.oot.Recruitment.Enumerated.EducationType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Education {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    @Enumerated(EnumType.STRING)
	private EducationType education_type;
	private String institute_name;
	private String qualification_name;
}
