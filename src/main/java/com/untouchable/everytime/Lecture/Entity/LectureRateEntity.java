package com.untouchable.everytime.Lecture.Entity;

import com.untouchable.everytime.Enum.LectureStatus;
import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LectureRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LPK;

    @ManyToOne
    LectureEntity lecture;

    String content;
    int recommendCount;
    Long rate;

    // 10 1학기, 11 하계 계절학기, 20 2학기, 21 동계 계절학기
    int year;
    int semester;

    @Enumerated(EnumType.STRING)
    LectureStatus assignment;
    @Enumerated(EnumType.STRING)
    LectureStatus teamMeeting;
    @Enumerated(EnumType.STRING)
    LectureStatus score;


    @ManyToOne
    User user;

}
