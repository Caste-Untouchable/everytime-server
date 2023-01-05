package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.BoardRecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRecommendRepository extends JpaRepository<BoardRecommendEntity, Long> {
    List<BoardRecommendEntity> findByBoard_BoardPK(Long boardPK);



}
