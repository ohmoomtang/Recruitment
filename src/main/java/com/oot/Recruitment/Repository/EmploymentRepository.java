package com.oot.Recruitment.Repository;

import com.oot.Recruitment.Models.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {

}
