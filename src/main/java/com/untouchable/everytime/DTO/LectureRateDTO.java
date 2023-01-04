package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.LectureInfo;

import java.sql.Date;

public class LectureRateDTO {

    Long lectureRate_PK;

    Long lecture_PK;

    String content;
    Date lectureDate;
    int recommendCount;
    Long rate;
    String testInfo;

    LectureInfo lectureInfo;

}
