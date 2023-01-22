package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardComment;
import com.untouchable.everytime.Board.Entity.BoardCommentReport;
import com.untouchable.everytime.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentReportRepository extends JpaRepository<BoardCommentReport, Long> {
    @Query("select (count(b) > 0) from BoardCommentReport b where b.reportUser = ?1 and b.reportBoardComment = ?2")
    boolean existsByReportUserAndReportBoardComment(User reportUser, BoardComment reportBoardComment);

    @Query("select count(b) from BoardCommentReport b where b.reportBoardComment = ?1")
    long countByReportBoardComment(BoardComment reportBoardComment);


}
