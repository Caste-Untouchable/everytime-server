package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Board.Entity.BoardImageEntity;
import com.untouchable.everytime.Board.Entity.BoardTypeEntity;
import com.untouchable.everytime.Enum.BookStatus;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
public class BookStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookStore_PK;

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
    @OneToMany
    ArrayList<BoardImageEntity> images;
    Date createdAT;
    String author;
    boolean anonymity;
    int reportCount;

    @Column
    Long price;
    int bookStatus;
    String location;

    BookStatus underLined;
    BookStatus written;
    boolean external;
    boolean naming;
    boolean colorChanged;
    boolean damaged;
    boolean delivery;
    boolean direct;
    String meetingPlaces;
    int sellPrice;

}
