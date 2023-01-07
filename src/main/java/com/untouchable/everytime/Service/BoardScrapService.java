package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.BoardScrapDTO;
import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Entity.BoardScrapEntity;
import com.untouchable.everytime.Repository.BoardRepository;
import com.untouchable.everytime.Repository.BoardScrapRepository;
import com.untouchable.everytime.Repository.UserRepository;
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
    public BoardScrapService(UserRepository userRepository,BoardRepository boardRepository,BoardScrapRepository boardScrapRepository,JwtConfig jwtConfig,ModelMapper standardMapper) {
        this.boardScrapRepository = boardScrapRepository;
        this.jwtConfig = jwtConfig;
        this.modelMapper = standardMapper;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ArrayList<BoardScrapDTO> getMyScrap(String token){
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        List<BoardScrapEntity> resultEntity = boardScrapRepository.findByUser_UserID(String.valueOf(jwt.get("ID")));
        ArrayList<BoardScrapDTO> resultDTOs = new ArrayList<>();

        for (BoardScrapEntity entity : resultEntity) {
            resultDTOs.add(modelMapper.map(entity, BoardScrapDTO.class));
        }

        return resultDTOs;
    }

    public ResponseEntity scrapBoard(Long id, String token){
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        if (boardEntity.isPresent() && boardEntity.get().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            // 스크랩 히스토리 저장
            BoardScrapEntity boardScrapEntity = new BoardScrapEntity();
            boardScrapEntity.setBoard(boardEntity.get());
            boardScrapEntity.setUser(userRepository.findById(String.valueOf(jwt.get("ID"))).get());
            boardScrapRepository.save(boardScrapEntity);

            // 스크랩 카운트 증가
            boardEntity.get().setScrapCount(boardEntity.get().getScrapCount() + 1);
            boardRepository.save(boardEntity.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}

