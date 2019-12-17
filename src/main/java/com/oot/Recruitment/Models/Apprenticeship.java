package com.oot.Recruitment.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Apprenticeship {
    @Id
    private Long id;
    private int total_points;
    @OneToOne(mappedBy = "apprenticeship")
    private EOI eoi;
}
