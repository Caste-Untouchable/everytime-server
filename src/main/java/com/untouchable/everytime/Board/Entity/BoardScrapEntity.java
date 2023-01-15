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

public class BoardScrapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardScarpPK;

    @ManyToOne
    BoardEntity board;
    
    @ManyToOne
    User user;

}
