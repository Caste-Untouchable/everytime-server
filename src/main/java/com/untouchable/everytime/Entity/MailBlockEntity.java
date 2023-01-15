package com.untouchable.everytime.Entity;

import com.untouchable.everytime.User.Entity.User;
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
    private Long mailBlock_PK;

    @ManyToOne
    User blockUser;

    @ManyToOne
    User blockedUser;

}
