package com.untouchable.everytime.Schedule.Repository;

import com.untouchable.everytime.Schedule.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
    @Query("select s from ScheduleEntity s where s.lecture.school.schoolName = ?1")
    List<ScheduleEntity> findByLecture_School_SchoolName(String schoolName);

    @Query("select s from ScheduleEntity s where s.lecture.lecturePK = ?1")
    List<ScheduleEntity> findByLecture_LecturePK(Long lecturePK);


}
