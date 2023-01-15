package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardTypeRepository extends JpaRepository<BoardTypeEntity, Long> {
    List<BoardTypeEntity> findBySchool_SchoolName(String SchoolName);






}
