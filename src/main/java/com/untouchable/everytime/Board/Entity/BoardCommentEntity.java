package com.untouchable.everytime.Board.Entity;

import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCommentPK;
    @ManyToOne
    BoardEntity board;
    @ManyToOne
    User user;
    boolean anonymity;
    Timestamp createdAT;
    Long reportCount;
    String content;
    Long replyTo;
}
