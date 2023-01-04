package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Entity.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

public class BoardCommentDTO {

    private Long boardComment_PK;

    Long board_PK;

    Long user_PK;

    boolean anonymity;
    Boolean isReply;
    String author;
    Date createdAT;
    Long reportCount;
    String content;
    Long reportNum;

}
