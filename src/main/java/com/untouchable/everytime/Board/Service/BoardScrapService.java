package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Board.DTO.BoardResponseDTO;
import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.DTO.BoardScrapDTO;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardScrap;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.Board.Repository.BoardScrapRepository;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardScrapService {
    BoardScrapRepository boardScrapRepository;
    UserRepository userRepository;
    BoardRepository boardRepository;
    JwtConfig jwtConfig;
    ModelMapper modelMapper;

    @Autowired
    public BoardScrapService(UserRepository userRepository,BoardRepository boardRepository,BoardScrapRepository boardScrapRepository,JwtConfig jwtConfig,ModelMapper modelMapper) {
        this.boardScrapRepository = boardScrapRepository;
        this.jwtConfig = jwtConfig;
        this.modelMapper = modelMapper;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<ArrayList<BoardResponseDTO>> getMyScrap(String token){
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (userEntity.isEmpty()) {
            return null;
        }

        List<BoardScrap> resultEntity = boardScrapRepository.findByUser(userEntity.get());
        ArrayList<BoardResponseDTO> result = new ArrayList<>();

        for (BoardScrap entity : resultEntity) {
            BoardResponseDTO boardResponseDTO = modelMapper.map(entity.getBoard(), BoardResponseDTO.class);
            if (boardResponseDTO.isAnonymity()){
                boardResponseDTO.setUserUserNickname("null");
                boardResponseDTO.setUserUserId("null");
            }
            result.add(boardResponseDTO);
        }
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> scrapBoard(Long id, String token){
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        // Null Check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));

        if (boardEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // ?????? ??????
        if (!boardEntity.get().getSchool().equals(userEntity.get().getUserSchool())) {
            System.out.println("?????? ?????????");
            return ResponseEntity.badRequest().build();
        }
        // ?????? ??????
        if (boardScrapRepository.findByBoardAndUser(boardEntity.get(), userEntity.get()).isPresent()) {
            System.out.println("?????? ?????????");
            return ResponseEntity.badRequest().build();
        }

        // ????????? ???????????? ??????
        BoardScrap boardScrap = new BoardScrap();
        boardScrap.setBoard(boardEntity.get());
        boardScrap.setUser(userEntity.get());
        boardScrapRepository.save(boardScrap);

        // ????????? ????????? ??????
        Long scrapCount = boardScrapRepository.countByBoard(boardEntity.get());
        boardEntity.get().setScrapCount(scrapCount);
        boardRepository.save(boardEntity.get());
        
        return ResponseEntity.ok("????????? ??????");
    }

    public ResponseEntity<String> unScrapBoard(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        // Null Check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (boardEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ????????? ??????
        Optional<BoardScrap> boardScrap = boardScrapRepository.findByBoardAndUser(boardEntity.get(), userEntity.get());
        if (boardScrap.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // ????????? ???????????? ??????
        boardScrapRepository.delete(boardScrap.get());

        // ????????? ????????? ??????
        Long scrapCount = boardScrapRepository.countByBoard(boardEntity.get());
        boardEntity.get().setScrapCount(scrapCount);
        boardRepository.save(boardEntity.get());

        return ResponseEntity.ok("????????? ?????? ??????");
    }
}

