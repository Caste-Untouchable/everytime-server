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
    Long LPK;

    @Enumerated(EnumType.STRING)
    LectureType lectureType;

    @Column
    int lectureNum;
    int separation;
    String lectureName;
    int credit;
    int grade;
    String professor;

    @ManyToMany
    ArrayList<BookEntity> book;

    @ManyToOne
    ScheduleEntity schedule;

}
