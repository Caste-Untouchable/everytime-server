package com.untouchable.everytime.Mail.Entity;


import com.untouchable.everytime.User.Entity.User;
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
public class MailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mail_PK;

    @ManyToOne
    User receiver;
    @ManyToOne
    User sender;
    String comment;
    Date createdAT;

    @ManyToOne
    MailRoomEntity mailRoom;

}
