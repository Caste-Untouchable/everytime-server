package com.untouchable.everytime.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder

public class ScheduleDTO {

    Long schedule_PK;

    Long user_PK;

    Long school_PK;

}
