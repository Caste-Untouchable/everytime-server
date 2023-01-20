package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardReportRepository extends JpaRepository<BoardReport, Long> {

}
