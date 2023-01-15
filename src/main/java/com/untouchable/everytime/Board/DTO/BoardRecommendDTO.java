package com.untouchable.everytime.Board.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRecommendDTO {

    Long boardRecommendPK;

    Long boardPK;

    String UserID;

}
