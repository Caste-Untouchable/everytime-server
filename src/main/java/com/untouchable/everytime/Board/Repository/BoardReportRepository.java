package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardReport;
import com.untouchable.everytime.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardReportRepository extends JpaRepository<BoardReport, Long> {
    @Query("select (count(b) > 0) from BoardReport b where b.reportUser = ?1 and b.reportBoard = ?2")
    boolean existsByReportUserAndReportBoard(User reportUser, Board reportBoard);

    @Query("select count(b) from BoardReport b where b.reportBoard = ?1")
    long countByReportBoard(Board reportBoard);
}
