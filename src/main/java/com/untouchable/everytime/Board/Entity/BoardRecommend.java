package com.untouchable.everytime.Board.Entity;

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
public class BoardRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardRecommendPK;
    @ManyToOne
    Board board;
    @ManyToOne
    User user;
}
