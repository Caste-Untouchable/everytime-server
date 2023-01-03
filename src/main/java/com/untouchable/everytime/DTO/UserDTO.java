package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.UserStatus;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    Long UPK;
    String ID;
    String PWD;
    String name;
    String nickname;
    String email;
    //SchoolEntity schoolEntity;
    Date year;

    UserProfileDTO userProfile;

    boolean verified;
    Long point;

    private UserStatus status;
}
