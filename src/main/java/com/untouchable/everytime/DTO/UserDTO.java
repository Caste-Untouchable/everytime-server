package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.UserStatus;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    String ID;
    String PWD;
    String name;
    String nickname;
    String email;

    Long school_PK;
    Date year;

    boolean verified;
    Long point;

    UserStatus status;
}
