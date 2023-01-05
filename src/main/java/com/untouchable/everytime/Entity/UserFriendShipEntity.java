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
public class UserFriendShipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFriendshipPK;

    @ManyToOne
    UserEntity user1;
    @ManyToOne
    UserEntity user2;
}