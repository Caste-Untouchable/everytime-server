package com.untouchable.everytime.Mail.Entity;

import com.untouchable.everytime.User.Entity.User;
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
    User user1;
    @ManyToOne
    User user2;
    @OneToMany
    ArrayList<MailEntity> mailList;

}
