package com.untouchable.everytime.Schedule.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDTO {

    private Long schedulePK;

    Long lecturePK;

    String location;

    // 일요일 0 ~ 토요일 6
    int day;

    // 0시 ~ 24시 , start < end
    int start;
    int end;

}
