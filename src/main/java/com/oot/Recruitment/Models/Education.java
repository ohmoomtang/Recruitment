package com.oot.Recruitment.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Education {
	
	@Id
	private Long id;
	private int education_type;
	private String institute_name;
	private String qualification_name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eoi_id")
	private EOI eoi;
}
