package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {
    @Query("select b from BoardCommentEntity b where b.board.boardPK = ?1")
    List<BoardCommentEntity> findByBoard_BoardPK(Long boardPK);


}

