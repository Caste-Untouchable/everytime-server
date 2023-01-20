package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {
    List<BoardType> findBySchool_SchoolName(String SchoolName);
}
