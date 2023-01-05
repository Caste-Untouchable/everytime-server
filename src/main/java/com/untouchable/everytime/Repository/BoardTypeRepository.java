package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.BoardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface BoardTypeRepository extends JpaRepository<BoardTypeEntity, Long> {
    List<BoardTypeEntity> findBySchool_SchoolName(String SchoolName);




}
