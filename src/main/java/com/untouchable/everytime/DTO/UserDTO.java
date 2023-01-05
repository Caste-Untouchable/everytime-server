package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.UserStatus;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    String userID;
    String PWD;
    String name;
    String nickname;
    String email;
    String schoolName;
    int registeredYear;
    boolean verified;
    Long point;
    UserStatus status;
}
