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

public class BoardRecommendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long BoardRecommend_PK;

    @ManyToOne
    UserEntity user;
}
