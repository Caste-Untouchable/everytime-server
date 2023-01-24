package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    @Query("select b from BoardComment b where b.board.boardPk = ?1")
    List<BoardComment> findByBoard_BoardPk(Long boardPk);
}

