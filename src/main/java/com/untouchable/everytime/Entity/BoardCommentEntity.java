package com.untouchable.everytime.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BoardCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long CPK;

    @ManyToOne
    BoardEntity board;

    @ManyToOne
    UserEntity user;

    boolean anonymity;
    Boolean isReply;
    String author;
    Date createdAT;
    Long reportCount;
    String content;
    Long reportNum;

    @Nullable
    Long replyTo;

}
