package com.untouchable.everytime.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureDTO {

    Long Lecture_PK;

    Long lecture_PK;

    int lectureNum;
    int separation;
    String lectureName;
    int credit;
    int grade;
    String professor;

    ArrayList<String> book_PK;

    Long schedule_PK;

}
