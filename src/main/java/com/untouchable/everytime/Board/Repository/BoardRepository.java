package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByBoardType_BoardTypePK(Long boardTypePK);



}
