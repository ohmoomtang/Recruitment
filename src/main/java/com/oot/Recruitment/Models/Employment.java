package com.oot.Recruitment.Models;

import java.sql.Date;

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
public class Employment {
	@Id
	private Long id;
	private String title;
	private String company;
	private boolean relevant;
	private Date start_date;
	private Date end_date;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eoi_id")
	private EOI eoi;
}
