package com.untouchable.everytime.Lecture.Entity;

import com.untouchable.everytime.Enum.LectureType;
import com.untouchable.everytime.School.Entity.School;
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
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturePK;

    @Enumerated(EnumType.STRING)
    LectureType lectureType;

    @ManyToOne
    School school;

    int lectureNum;
    int separation;
    String lectureName;
    int credit;
    int grade;
    String professor;

    Long average;

    // 10 1학기, 11 하계 계절학기, 20 2학기, 21 동계 계절학기
    @Column(name = "Lecture_Year")
    int year;
    int semester;

//    @ManyToMany
//    ArrayList<BookEntity> book;

//    @OneToMany
//    ArrayList<ScheduleEntity> schedule;

    // 최신 2개만 가져오기
    // TODO : Custom Mapper 만들기
//    @OneToMany
//    ArrayList<LectureRateEntity> lectureRateList;

}
