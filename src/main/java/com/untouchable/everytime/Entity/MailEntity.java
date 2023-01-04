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
    Long Mail_PK;

    @Column
    String reciever;
    String sender;
    String comment;
    Date createdAT;

}
