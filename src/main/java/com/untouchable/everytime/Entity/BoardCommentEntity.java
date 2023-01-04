package com.untouchable.everytime.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardComment_PK;
    @ManyToOne
    BoardEntity board;
    @ManyToOne
    UserEntity user;
    boolean anonymity;
    Date createdAT;
    Long reportCount;
    String content;
    Long replyTo;

}
