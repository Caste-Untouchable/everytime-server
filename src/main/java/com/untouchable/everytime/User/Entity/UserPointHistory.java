package com.untouchable.everytime.User.Entity;

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
public class UserPointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPointHistoryPk;
    @ManyToOne
    User user;
    //@ManyToOne
    //TODO : 강의평가 클래스 넣기
    Long point;
}
