package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    String ID;
    String PWD;
    String name;
    String nickname;
    String email;
    @ManyToOne
    SchoolEntity school;
    Date year;
    @OneToOne
    UserProfileEntity userProfile;
    boolean verified;
    Long point;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}