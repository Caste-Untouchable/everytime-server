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
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCommentPK;
    @ManyToOne
    Board board;
    @ManyToOne
    User user;
    boolean anonymity;
    Timestamp createdAT;
    Long reportCount;
    Long recommendCount;
    String content;
    Long replyTo;
}
