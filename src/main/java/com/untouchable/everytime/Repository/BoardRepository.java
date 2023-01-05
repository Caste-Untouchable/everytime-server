package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByBoardType_BoardTypePK(Long boardTypePK);



}
