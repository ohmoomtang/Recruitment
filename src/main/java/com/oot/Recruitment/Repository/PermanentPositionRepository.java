package com.oot.Recruitment.Repository;

import com.oot.Recruitment.Models.PermanentPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermanentPositionRepository extends JpaRepository<PermanentPosition, Long> {

}
