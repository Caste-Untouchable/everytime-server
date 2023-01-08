package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Entity.LectureEntity;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Enum.LectureStatus;
import jakarta.persistence.*;
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

    Long LPK;

    Long lectureLecturePK;

    String content;
    int recommendCount;
    Long rate;
    // 10 1학기, 11 하계 계절학기, 20 2학기, 21 동계 계절학기
    int year;
    int semester;


    LectureStatus assignment;
    LectureStatus teamMeeting;
    LectureStatus score;

    String userUserID;






}
