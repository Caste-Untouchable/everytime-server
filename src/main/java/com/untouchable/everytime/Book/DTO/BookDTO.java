package com.untouchable.everytime.Book.DTO;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    Long book_PK;

    String title;
    String author;
    String publisher;
    Date publicationDate;
    Long price;

}
