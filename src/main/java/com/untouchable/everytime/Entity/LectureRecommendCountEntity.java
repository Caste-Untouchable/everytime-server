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

public class LectureRecommendCountEntity {

    @Id
    private Long LectureRecommendCount_PK;

    @ManyToOne
    BoardEntity board;

    @ManyToOne
    UserEntity user;

}
