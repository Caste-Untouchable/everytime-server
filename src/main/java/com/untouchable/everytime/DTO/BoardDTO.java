package com.untouchable.everytime.DTO;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardDTO {

    private Long boardPK;

    Long schoolPK;

    String SchoolName;

    BoardTypeDTO boardType;

    String boardTitle;
    String content;
    int recommendCount;

    ArrayList<String> storeFilename;

    Date createdAT;
    String author;
    boolean anonymity;
    Long reportCount;

}
