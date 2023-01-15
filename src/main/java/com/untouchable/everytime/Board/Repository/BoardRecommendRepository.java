package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardRecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRecommendRepository extends JpaRepository<BoardRecommendEntity, Long> {
    List<BoardRecommendEntity> findByBoard_BoardPK(Long boardPK);



}
