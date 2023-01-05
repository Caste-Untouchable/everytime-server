package com.untouchable.everytime.DTO;



import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCommentDTO {
    private Long boardCommentPK;
    Long boardPK;
    String nickname;
    String UserID;
    boolean anonymity;
    Timestamp createdAT;
    Long reportCount;
    String content;
    Long replyTo;

}
