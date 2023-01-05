package com.untouchable.everytime.Repository;

import com.untouchable.everytime.Entity.UserFriendShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFriendShipRepository extends JpaRepository<UserFriendShipEntity, Long> {
    List<UserFriendShipEntity> findByUser1_UserIDOrUser2_UserID(String userID1, String userID2);



}
