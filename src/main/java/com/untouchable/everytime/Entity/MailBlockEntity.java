package com.untouchable.everytime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MailBlockEntity {

    @Id
    Long MailBlock_PK;

    @ManyToOne
    UserEntity blockUser;

    @ManyToOne
    UserEntity blockedUser;

}
