package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardScrapRepository extends JpaRepository<BoardScrapEntity, Long> {



    @Query("select b from BoardScrapEntity b where b.user.userId = ?1")
    List<BoardScrapEntity> findByUser_UserId(String userId);






}
