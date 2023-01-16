package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardCommentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentReportRepository extends JpaRepository<BoardCommentReport, Long> {


}
