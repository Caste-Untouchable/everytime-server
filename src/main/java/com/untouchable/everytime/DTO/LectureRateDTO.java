package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.LectureStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    Long lectureRatePK;

    Long lecturePK;

    String content;
    int recommendCount;
    Long rate;
    LectureStatus assignmentStatus;
    LectureStatus teamMeetingStatus;
    LectureStatus scoreStatus;
    String userID;

}
