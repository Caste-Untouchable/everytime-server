package com.untouchable.everytime.Board.DTO;



import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCommentResponseDTO {
    private Long boardCommentPK;
    Long boardBoardPK;
    String userUserID;
    String userUserNickname;
    boolean anonymity;
    Timestamp createdAT;
    Long reportCount;
    String content;
    Long replyTo;

}
