package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Entity.LectureEntity;
import com.untouchable.everytime.Entity.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ScheduleCustomDTO {


    private Long ScheduleCustom_PK;


    Long lecture_PK;

    String location;

    // 일요일 0 ~ 토요일 6
    int day;

    // 0시 ~ 24시 , start < end
    int start;
    int end;


    String userID;
}
