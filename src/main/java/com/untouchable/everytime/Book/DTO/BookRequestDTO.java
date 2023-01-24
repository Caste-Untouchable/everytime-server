package com.untouchable.everytime.Book.DTO;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDTO {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Timestamp publicationDate;
    private Long bookPrice;
}
