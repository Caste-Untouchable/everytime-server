package com.untouchable.everytime.DTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardScrapDTO {

    Long BoardScrapPK;

    Long board_PK;

    Long user_PK;

}
