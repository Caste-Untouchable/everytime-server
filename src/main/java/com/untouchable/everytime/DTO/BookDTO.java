package com.untouchable.everytime.DTO;

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
