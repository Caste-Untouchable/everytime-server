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
public class UserPointHistoryEntity {
    @Id
    private Long PK;

    @ManyToOne
    UserEntity userEntity;

    //@ManyToOne
    //TODO : 강의평가 클래스 넣기

    Long point;

}
