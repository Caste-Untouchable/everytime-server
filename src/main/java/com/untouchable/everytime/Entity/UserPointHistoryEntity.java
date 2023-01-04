package com.untouchable.everytime.Entity;

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
    private Long PK;

    @ManyToOne
    UserEntity user;

    //@ManyToOne
    //TODO : 강의평가 클래스 넣기

    Long point;

}
