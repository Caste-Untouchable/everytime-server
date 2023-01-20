package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

}
