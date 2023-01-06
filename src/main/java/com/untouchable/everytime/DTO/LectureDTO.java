package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.LectureType;
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

    Long lecturePK;
    LectureType lectureType;
    String schoolName;
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

    //ArrayList<String> book_PK;

    //Long schedule_PK;

}
