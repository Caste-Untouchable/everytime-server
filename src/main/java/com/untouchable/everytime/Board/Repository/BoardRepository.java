package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByBoardType_BoardTypePK(Long boardTypePK);

    @Transactional
    @Modifying
    @Query("update Board b set b.user = ?1 where b.user = ?2")
    int updateUserByUser(@Nullable User user, User user1);
}
