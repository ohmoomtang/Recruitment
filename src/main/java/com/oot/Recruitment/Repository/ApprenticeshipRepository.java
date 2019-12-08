package com.oot.Recruitment.Repository;

import com.oot.Recruitment.Models.Apprenticeship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenticeshipRepository extends JpaRepository<Apprenticeship, Long> {

}
