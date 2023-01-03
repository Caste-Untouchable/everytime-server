package com.untouchable.everytime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.nio.file.AccessMode;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MySchedule {
    @Id
    Long pk;

    @ManyToOne
    UserEntity userEntity;

    Date date;
}
