package com.untouchable.everytime.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFriendshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFriendship_PK;

    //TODO : ModelMapper Custom 만들기
    @ManyToOne
    UserEntity user1;
    @ManyToOne
    UserEntity user2;
}
