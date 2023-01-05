package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.UserFriendShipDTO;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Entity.UserFriendShipEntity;
import com.untouchable.everytime.Repository.UserFriendShipRepository;
import com.untouchable.everytime.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserFriendShipService {

    UserFriendShipRepository userFriendShipRepository;
    UserRepository userRepository;
    JwtConfig jwtConfig;

    @Autowired
    public UserFriendShipService(JwtConfig jwtConfig, UserRepository userRepository, UserFriendShipRepository userFriendShipRepository) {
        this.userFriendShipRepository = userFriendShipRepository;
        this.userRepository = userRepository;
        this.jwtConfig = jwtConfig;
    }

    public ResponseEntity createFriendShip(String id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);


        List<UserFriendShipEntity> resultEntity = userFriendShipRepository.findByUser1_UserIDOrUser2_UserID(String.valueOf(jwt.get("ID")), String.valueOf(jwt.get("ID")));

        for (UserFriendShipEntity entity : resultEntity) {
            if (entity.getUser1().getUserID().equals(id) || entity.getUser2().getUserID().equals(id)) {
                return ResponseEntity.badRequest().body("이미 친구입니다.");
            }
        }

        Optional<UserEntity> targetFriend = userRepository.findById(id);

        if (targetFriend.isPresent()) {
            UserFriendShipEntity userFriendShipEntity = new UserFriendShipEntity();
            userFriendShipEntity.setUser1(userRepository.findById(String.valueOf(jwt.get("ID"))).get());
            userFriendShipEntity.setUser2(targetFriend.get());

            userFriendShipRepository.save(userFriendShipEntity);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();

        }

    }

    public ResponseEntity<ArrayList<UserFriendShipDTO>> findFriendShip(String userID) {
        List<UserFriendShipEntity> resultEntity = userFriendShipRepository.findByUser1_UserIDOrUser2_UserID(userID, userID);

        if (resultEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ArrayList result = new ArrayList();
        for (UserFriendShipEntity entity : resultEntity) {
            UserEntity user1 = entity.getUser1();
            UserEntity user2 = entity.getUser2();
            UserFriendShipDTO dto = UserFriendShipDTO.builder()
                    .userFriendshipPK(entity.getUserFriendshipPK())
                    .user1(user1.getUserID())
                    .user2(user2.getUserID())
                    .build();
            result.add(dto);
        }

        return ResponseEntity.ok(result);

    }

    public UserFriendShipDTO toDTO(UserFriendShipEntity entity) {
        return UserFriendShipDTO.builder()
                .userFriendshipPK(entity.getUserFriendshipPK())
                .user1(entity.getUser1().getUserID())
                .user2(entity.getUser2().getUserID())
                .build();
    }

    public UserFriendShipEntity toEntity(UserFriendShipDTO dto) {
        return UserFriendShipEntity.builder()
                .userFriendshipPK(dto.getUserFriendshipPK())
                .user1(UserEntity.builder().userID(dto.getUser1()).build())
                .user2(UserEntity.builder().userID(dto.getUser2()).build())
                .build();
    }


}
