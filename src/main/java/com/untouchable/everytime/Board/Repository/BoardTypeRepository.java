package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardType;
import com.untouchable.everytime.School.Entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {
    List<BoardType> findBySchool_SchoolName(String SchoolName);
    @Query("select b from BoardType b where b.school = ?1")
    List<BoardType> findBySchool(School school);
}
