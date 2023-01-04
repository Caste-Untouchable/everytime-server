package com.untouchable.everytime.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MySchedule_PK;

    @ManyToOne
    UserEntity userEntity;

    Date date;
}
