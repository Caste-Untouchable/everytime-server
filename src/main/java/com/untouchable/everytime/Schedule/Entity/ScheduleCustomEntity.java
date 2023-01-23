package com.untouchable.everytime.Schedule.Entity;

import com.untouchable.everytime.Lecture.Entity.LectureEntity;
import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ScheduleCustomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ScheduleCustom_PK;

    @ManyToOne
    LectureEntity lecture;

    String location;

    // 일요일 0 ~ 토요일 6
    @Column(name = "Schedule_Day")
    int day;

    // 0시 ~ 24시 , start < end
    @Column(name = "start_time")
    int start;
    @Column(name = "end_time")
    int end;

    @ManyToOne
    User user;

}
