package com.untouchable.everytime.Entity;

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
    int day;

    // 0시 ~ 24시 , start < end
    int start;
    int end;

    @ManyToOne
    User user;

}
