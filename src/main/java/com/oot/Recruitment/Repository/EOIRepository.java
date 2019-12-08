package com.oot.Recruitment.Repository;

import com.oot.Recruitment.Models.EOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EOIRepository extends JpaRepository<EOI, Long> {

}
