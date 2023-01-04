package com.untouchable.everytime.Entity;

import com.untouchable.everytime.DTO.BoardDTO;
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
    private Long boardRecommend_PK;

    @ManyToOne
    BoardEntity board;

    @ManyToOne
    UserEntity user;
}
