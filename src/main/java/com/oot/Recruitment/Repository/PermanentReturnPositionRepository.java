package com.oot.Recruitment.Repository;

import com.oot.Recruitment.Models.PermanentReturnPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermanentReturnPositionRepository extends JpaRepository<PermanentReturnPosition, Long> {

}
