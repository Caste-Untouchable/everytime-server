package com.untouchable.everytime.Lecture.Repository;

import com.untouchable.everytime.Lecture.Entity.LectureRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRateRepository extends JpaRepository<LectureRateEntity, Long> {
}
