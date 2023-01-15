package com.untouchable.everytime.Entity;

import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPointHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPoint_PK;

    @ManyToOne
    User user;

    //@ManyToOne
    //TODO : 강의평가 클래스 넣기

    Long point;

}
