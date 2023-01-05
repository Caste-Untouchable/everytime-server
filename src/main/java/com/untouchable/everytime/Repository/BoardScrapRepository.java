package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.BoardScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardScrapRepository extends JpaRepository<BoardScrapEntity, Long> {
    ;

    List<BoardScrapEntity> findByUser_UserID(String UserID);



}
