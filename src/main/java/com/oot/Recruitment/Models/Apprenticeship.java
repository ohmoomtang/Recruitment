package com.oot.Recruitment.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Apprenticeship {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int total_points;
}
