package com.untouchable.everytime.DTO;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Long boardPK;

    String schoolName;

    String UserID;
    String nickname;

    Long boardTypePK;

    String boardTitle;
    String content;
    int recommendCount;

//    ArrayList<BoardImageDTO> images;

    Timestamp createdAT;
    boolean anonymity;
    Long reportCount;

}
