package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String userId;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userEmail;
    @ManyToOne
    private SchoolEntity userSchool;
    private int userRegisteredYear;
    @OneToOne
    private UserProfileEntity userProfile;
    private boolean userSchoolVerified;
    Long userPoint;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
}
