package com.untouchable.everytime.Schedule.Entity;

import com.untouchable.everytime.Lecture.Entity.LectureEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedulePK;

    @ManyToOne
    LectureEntity lecture;

    String location;

    // 일요일 0 ~ 토요일 6
    int day;

    // 0시 ~ 24시 , start < end
    int start;
    int end;



}
