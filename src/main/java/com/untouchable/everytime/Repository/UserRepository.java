package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByIDAndPWD(String ID, String PWD);

}


