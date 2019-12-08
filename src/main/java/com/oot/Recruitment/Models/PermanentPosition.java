package com.oot.Recruitment.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class PermanentPosition {
    @Id
    private Long id;
    private int total_points;
    @OneToOne(mappedBy = "permanent_pos")
    private EOI eoi;
}
