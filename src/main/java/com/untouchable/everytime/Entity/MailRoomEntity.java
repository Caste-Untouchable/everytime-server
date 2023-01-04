package com.untouchable.everytime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailRoomEntity {
    @Id
    private Long mailRoom_PK;
    boolean anonymity;
    @ManyToOne
    UserEntity user1;
    @ManyToOne
    UserEntity user2;
    @OneToMany
    ArrayList<MailEntity> mailList;

}
