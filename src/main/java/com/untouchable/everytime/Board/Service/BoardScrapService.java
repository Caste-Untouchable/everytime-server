package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.DTO.BoardScrapDTO;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardScrap;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.Board.Repository.BoardScrapRepository;
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

    public ArrayList<BoardScrapDTO> getMyScrap(String token){
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        List<BoardScrap> resultEntity = boardScrapRepository.findByUser_UserId(String.valueOf(jwt.get("userId")));
        ArrayList<BoardScrapDTO> resultDTOs = new ArrayList<>();

        for (BoardScrap entity : resultEntity) {
            resultDTOs.add(modelMapper.map(entity, BoardScrapDTO.class));
        }

        return resultDTOs;
    }

    public ResponseEntity scrapBoard(Long id, String token){
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<Board> boardEntity = boardRepository.findById(id);
        if (boardEntity.isPresent() && boardEntity.get().getSchool().getSchoolName().equals(jwt.get("userSchool"))) {
            // 스크랩 히스토리 저장
            BoardScrap boardScrap = new BoardScrap();
            boardScrap.setBoard(boardEntity.get());
            boardScrap.setUser(userRepository.findById(String.valueOf(jwt.get("userId"))).get());
            boardScrapRepository.save(boardScrap);

            // 스크랩 카운트 증가
            boardEntity.get().setScrapCount(boardEntity.get().getScrapCount() + 1);
            boardRepository.save(boardEntity.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}

