package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Entity.BoardRecommendEntity;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Repository.BoardRecommendRepository;
import com.untouchable.everytime.Repository.BoardRepository;
import com.untouchable.everytime.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardRecommendService {

    JwtConfig jwtConfig;
    BoardRecommendRepository boardRecommendRepository;
    BoardRepository boardRepository;
    UserRepository userRepository;

    @Autowired
    public BoardRecommendService(UserRepository userRepository, JwtConfig jwtConfig, BoardRecommendRepository boardRecommendRepository, BoardRepository boardRepository) {
        this.jwtConfig = jwtConfig;
        this.boardRecommendRepository = boardRecommendRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> recommendBoardComment(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        // 이미 추천한 경우가 있는지 확인
        List<BoardRecommendEntity> result = boardRecommendRepository.findByBoard_BoardPK(id);
        if (result.size() > 0) {
            for (BoardRecommendEntity boardRecommendEntity : result) {
                if (boardRecommendEntity.getUser().getUserID().equals(jwt.get("ID"))) {

                    return ResponseEntity.badRequest().body("Already recommended");
                }
            }
        }

        BoardRecommendEntity boardRecommendEntity = new BoardRecommendEntity();

        Optional<BoardEntity> user = boardRepository.findById(id);
        if (user.isPresent()) {
            boardRecommendEntity.setBoard(user.get());
        }
        Optional<UserEntity> userEntity = userRepository.findById(jwt.get("ID").toString());
        if (userEntity.isPresent()) {
            boardRecommendEntity.setUser(userEntity.get());
        }

        boardRecommendRepository.save(boardRecommendEntity);

        return ResponseEntity.ok().body("친구 추가 완료");

    }


}
