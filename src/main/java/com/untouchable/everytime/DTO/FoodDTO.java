package com.untouchable.everytime.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FoodDTO {

    Long food_PK;

    Long school_PK;
    String where;
    String menu;
    Date date;

}
