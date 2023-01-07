package com.untouchable.everytime.Service;


import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.BoardDTO;
import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Repository.BoardRepository;
import com.untouchable.everytime.Repository.SchoolRepository;
import com.untouchable.everytime.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {

    BoardRepository boardRepository;
    SchoolRepository schoolRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;

    @Autowired
    public BoardService(UserRepository userRepository,SchoolRepository schoolRepository,BoardRepository boardRepository, ModelMapper modelMapper, JwtConfig jwtConfig) {
        this.boardRepository = boardRepository;
        this.schoolRepository = schoolRepository;
        this.modelMapper = modelMapper;
        this.jwtConfig = jwtConfig;
        this.userRepository = userRepository;
    }

    public BoardDTO createBoard(BoardDTO boardDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);

        boardEntity.setSchool(schoolRepository.findById(String.valueOf(jwt.get("SCHOOL"))).get());
        boardEntity.setUser(userRepository.findById(String.valueOf(jwt.get("ID"))).get());
        boardEntity.setCreatedAT(new Timestamp(System.currentTimeMillis()));
        boardEntity.setRecommendCount(0);

        BoardEntity result = boardRepository.save(boardEntity);
        return modelMapper.map(result, BoardDTO.class);
    }

    public Optional<BoardDTO> boardGetByIdWithSchool(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        if (boardEntity.isPresent() && boardEntity.get().getBoardType().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            return Optional.of(modelMapper.map(boardEntity.get(), BoardDTO.class));
        } else {
            return Optional.empty();
        }
    }

    public ArrayList<BoardDTO> boardGetByBoardType(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        List<BoardEntity> boardEntities = boardRepository.findByBoardType_BoardTypePK(id);
        if (!boardEntities.isEmpty() && boardEntities.get(0).getBoardType().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            ArrayList<BoardDTO> boardDTOS = new ArrayList<>();
            for (BoardEntity boardEntity : boardEntities) {
                boardDTOS.add(modelMapper.map(boardEntity, BoardDTO.class));
            }
            return boardDTOS;
        } else {
            return new ArrayList<BoardDTO>();
        }

    }


    public BoardDTO updateBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
        BoardEntity result = boardRepository.save(boardEntity);
        return modelMapper.map(result, BoardDTO.class);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
