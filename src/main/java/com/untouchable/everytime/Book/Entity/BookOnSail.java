package com.untouchable.everytime.Book.Entity;

import com.untouchable.everytime.Board.Entity.BoardImage;
import com.untouchable.everytime.Board.Entity.BoardType;
import com.untouchable.everytime.Book.Enum.BookStatus;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookOnSail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookOnSailPk;
    @ManyToOne
    School school;
    @ManyToOne
    User user;
    String boardTitle;
    String content;
    //    @OneToMany
    //    ArrayList<BoardImage> images;
    @ManyToOne
    Book book;
    Timestamp createdAT;
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
