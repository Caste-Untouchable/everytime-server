package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    @Query("select (count(u) > 0) from UserEntity u where u.userId = ?1")
    boolean existsByUserId(String userId);




}


