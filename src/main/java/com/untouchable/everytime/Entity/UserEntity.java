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
    String userID;
    String PWD;
    String name;
    String nickname;
    String email;
    @ManyToOne
    SchoolEntity school;
    int registeredYear;
    @OneToOne
    UserProfileEntity userProfile;
    boolean verified;
    Long point;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}