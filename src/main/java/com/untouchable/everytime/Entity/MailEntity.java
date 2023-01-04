package com.untouchable.everytime.Entity;


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
    UserEntity receiver;
    @ManyToOne
    UserEntity sender;
    String comment;
    Date createdAT;

    @ManyToOne
    MailRoomEntity mailRoom;

}
