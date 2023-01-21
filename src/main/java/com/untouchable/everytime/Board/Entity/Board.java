package com.untouchable.everytime.Board.Entity;

import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardPk;
    @ManyToOne
    School school;
    @ManyToOne
    User user;
    @ManyToOne
    BoardType boardType;
    @Column
    String boardTitle;
    String content;
    Long commentCount;
    Long recommendCount;
    Long scrapCount;
    Timestamp createdAT;
    boolean anonymity;
    Long reportCount;
    // @OneToMany
    // ArrayList<BoardImageEntity> images;
}
