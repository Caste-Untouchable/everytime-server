package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByBoardType_BoardTypePK(Long boardTypePK);

}
