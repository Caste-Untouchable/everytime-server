package com.untouchable.everytime.Lecture.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureRecommendCountDTO {

    Long lectureRecommendCount_PK;

    Long board_PK;

    Long user_PK;

}
