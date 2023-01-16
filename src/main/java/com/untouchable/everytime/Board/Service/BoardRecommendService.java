package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardRecommend;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.Board.Repository.BoardRecommendRepository;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.User.Repository.UserRepository;
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
        List<BoardRecommend> result = boardRecommendRepository.findByBoard_BoardPk(id);
        if (result.size() > 0) {
            for (BoardRecommend boardRecommend : result) {
                if (boardRecommend.getUser().getUserId().equals(jwt.get("ID"))) {

                    return ResponseEntity.badRequest().body("Already recommended");
                }
            }
        }

        BoardRecommend boardRecommend = new BoardRecommend();

        Optional<Board> user = boardRepository.findById(id);
        if (user.isPresent()) {
            boardRecommend.setBoard(user.get());
        }
        Optional<User> userEntity = userRepository.findById(jwt.get("ID").toString());
        if (userEntity.isPresent()) {
            boardRecommend.setUser(userEntity.get());
        }

        boardRecommendRepository.save(boardRecommend);

        return ResponseEntity.ok().body("친구 추가 완료");

    }


}
