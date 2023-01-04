package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.BoardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.util.ArrayList;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_PK;

    @ManyToOne
    SchoolEntity school;
    @ManyToOne
    UserEntity user;
    @Enumerated(EnumType.STRING)
    BoardType boardType;
    @Column
    String boardTitle;
    String content;
    int recommendCount;
    int scrapCount;
    @OneToMany
    ArrayList<BoardImageEntity> images;
    Date createdAT;
    String author;
    boolean anonymity;
    int reportCount;


}
