package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.LectureRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRateRepository extends JpaRepository<LectureRateEntity, Long> {
}
