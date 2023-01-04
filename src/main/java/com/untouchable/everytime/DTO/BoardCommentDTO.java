package com.untouchable.everytime.DTO;



import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCommentDTO {
    private Long boardComment_PK;
    Long board_PK;
    String nickname;
    String User_ID;
    boolean anonymity;
    Date createdAT;
    Long reportCount;
    String content;
    Long replyTo;

}
