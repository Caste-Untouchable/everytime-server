package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.UserStatus;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    String User_ID;
    String PWD;
    String name;
    String nickname;
    String email;
    String SchoolName;
    int registeredYear;
    boolean verified;
    Long point;
    UserStatus status;
}
