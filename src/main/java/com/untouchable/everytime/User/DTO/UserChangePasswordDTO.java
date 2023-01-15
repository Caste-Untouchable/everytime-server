package com.untouchable.everytime.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserChangePasswordDTO {
    private String userPwd;
    private String userNewPwd;
    private String userNewPwd2;
}
