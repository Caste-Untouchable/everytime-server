package com.untouchable.everytime.Book.DTO;

import com.untouchable.everytime.Book.Entity.Book;
import com.untouchable.everytime.Book.Enum.BookStatus;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookOnSailCreateDTO {
    String boardTitle;
    String content;
    //    @OneToMany
    //    ArrayList<BoardImage> images;
    String bookIsbn;
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
