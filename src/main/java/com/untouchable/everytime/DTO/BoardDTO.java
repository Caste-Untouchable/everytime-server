package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Entity.BoardImageEntity;
import com.untouchable.everytime.Entity.SchoolEntity;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Enum.BoardType;
import com.untouchable.everytime.Enum.UserStatus;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardDTO {

    Long PK;

    BoardType boardType;

    String boardTitle;
    String content;
    int recommendCount;

    Date createdAT;
    String author;
    boolean anonymity;
    Long reportCount;

}
