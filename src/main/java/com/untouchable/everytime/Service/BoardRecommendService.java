package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Entity.BoardRecommendEntity;
import com.untouchable.everytime.Repository.BoardRecommendRepository;
import com.untouchable.everytime.Repository.BoardRepository;
import com.untouchable.everytime.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardRecommendService {

    JwtConfig jwtConfig;
    BoardRecommendRepository boardRecommendRepository;
    BoardRepository boardRepository;
    UserRepository userRepository;

    @Autowired
    public BoardRecommendService(UserRepository userRepository,JwtConfig jwtConfig, BoardRecommendRepository boardRecommendRepository, BoardRepository boardRepository) {
        this.jwtConfig = jwtConfig;
        this.boardRecommendRepository = boardRecommendRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity recommendBoardComment(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        List<BoardRecommendEntity> result = boardRecommendRepository.findByBoard_BoardPK(id);

        for (BoardRecommendEntity boardRecommendEntity : result) {
            if (boardRecommendEntity.getUser().getUserID().equals(jwt.get("ID"))) {

                return ResponseEntity.badRequest().build();
            }
        }

        BoardRecommendEntity boardRecommendEntity = new BoardRecommendEntity();
        boardRecommendEntity.setBoard(boardRepository.findById(id).get());
        boardRecommendEntity.setUser(userRepository.findById(jwt.get("ID").toString()).get());
        boardRecommendRepository.save(boardRecommendEntity);

        return ResponseEntity.ok().build();

    }


}
