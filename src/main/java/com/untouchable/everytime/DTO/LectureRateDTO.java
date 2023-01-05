package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.LectureStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureRateDTO {

    Long lectureRate_PK;

    Long lecture_PK;

    String content;
    Date lectureDate;
    int recommendCount;
    Long rate;
    String testInfo;

    LectureStatus lectureInfo;

}
