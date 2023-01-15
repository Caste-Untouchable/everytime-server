package com.untouchable.everytime.School.DTO;

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
    Long foodPK;
    String schoolName;
    String place;
    String menu;
    Date date;

}
