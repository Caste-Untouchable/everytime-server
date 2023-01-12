package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String userId;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userSchoolSchoolName;
    private int userRegisteredYear;
    private String userProfile;
    private boolean userSchoolVerified;
    Long userPoint;
    private UserStatus userStatus;
}
