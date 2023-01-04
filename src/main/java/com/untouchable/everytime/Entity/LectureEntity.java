package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.LectureType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecture_PK;

    @Enumerated(EnumType.STRING)
    LectureType lectureType;

    @Column
    int lectureNum;
    int separation;
    String lectureName;
    int credit;
    int grade;
    String professor;

    Long averageRate;

    // 10 1학기, 11 하계 계절학기, 20 2학기, 21 동계 계절학기
    int year;
    int semester;

    @ManyToMany
    ArrayList<BookEntity> book;

    @OneToMany
    ArrayList<ScheduleEntity> schedule;

    // 최신 2개만 가져오기
    // TODO : Custom Mapper 만들기
    @OneToMany
    ArrayList<LectureRateEntity> lectureRateList;

}
