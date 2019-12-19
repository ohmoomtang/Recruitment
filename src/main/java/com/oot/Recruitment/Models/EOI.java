package com.oot.Recruitment.Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class EOI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Education> education = new ArrayList<Education>();
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Employment> employment = new ArrayList<Employment>();
    
    private int english_proficiency_level;
    
    private boolean permanent_pos_toggle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_pos_id")
    private PermanentPosition permanent_pos;
    
    private boolean permanent_return_pos_toggle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_return_pos_id")
    private PermanentReturnPosition permanent_return_pos;
    
    private boolean apprenticeship_toggle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apprenticeship_id")
    private Apprenticeship apprenticeship;
    
    private int status;
    
    @CreationTimestamp
    private Date create_date;
    @UpdateTimestamp
    private Date last_update_date;
}
