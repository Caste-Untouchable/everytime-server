package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardScrap;
import com.untouchable.everytime.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardScrapRepository extends JpaRepository<BoardScrap, Long> {
    @Query("select b from BoardScrap b where b.user = ?1")
    List<BoardScrap> findByUser(User user);
    @Query("select b from BoardScrap b where b.board = ?1 and b.user = ?2")
    Optional<BoardScrap> findByBoardAndUser(Board board, User user);
    @Query("select count(b) from BoardScrap b where b.board = ?1")
    long countByBoard(Board board);
}
