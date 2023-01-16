package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardScrapRepository extends JpaRepository<BoardScrap, Long> {
    @Query("select b from BoardScrap b where b.user.userId = ?1")
    List<BoardScrap> findByUser_UserId(String userId);

}
