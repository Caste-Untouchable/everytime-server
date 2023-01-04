package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.LectureStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LectureRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureRate_PK;

    @ManyToOne
    LectureEntity lecture;

    String content;
    Date lectureDate;
    int recommendCount;
    int rate;

    // 10 1학기, 11 하계 계절학기, 20 2학기, 21 동계 계절학기
    int year;
    int semester;

    @Enumerated(EnumType.STRING)
    LectureStatus assignmentStatus;
    @Enumerated(EnumType.STRING)
    LectureStatus teamMeetingStatus;
    @Enumerated(EnumType.STRING)
    LectureStatus scoreStatus;


    @ManyToOne
    UserEntity user;

}
