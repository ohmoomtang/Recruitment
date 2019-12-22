package com.oot.Recruitment.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
public class PermanentReturnPosition {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int total_points;
}
