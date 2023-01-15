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
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardPK;

    @ManyToOne
    School school;
    @ManyToOne
    User user;
    @ManyToOne
    BoardTypeEntity boardType;
    @Column
    String boardTitle;
    String content;
    int recommendCount;
    int scrapCount;
    //    @OneToMany
//    ArrayList<BoardImageEntity> images;
    Timestamp createdAT;
    boolean anonymity;
    int reportCount;


}
