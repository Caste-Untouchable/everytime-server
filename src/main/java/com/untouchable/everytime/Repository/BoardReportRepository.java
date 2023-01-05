package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.BoardReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardReportRepository extends JpaRepository<BoardReportEntity, Long> {

}
