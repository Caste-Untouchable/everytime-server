package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRecommendRepository extends JpaRepository<BoardRecommend, Long> {

    @Query("select b from BoardRecommend b where b.board.boardPk = ?1")
    List<BoardRecommend> findByBoard_BoardPk(Long boardPk);


}
