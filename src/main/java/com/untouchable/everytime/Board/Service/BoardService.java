package com.untouchable.everytime.Board.Service;


import com.untouchable.everytime.Board.DTO.BoardRequestDTO;
import com.untouchable.everytime.Board.Repository.BoardTypeRepository;
import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.DTO.BoardResponseDTO;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.Repository.UserRepository;
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
    BoardTypeRepository boardTypeRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;

    @Autowired
    public BoardService(BoardTypeRepository boardTypeRepository,UserRepository userRepository,SchoolRepository schoolRepository,BoardRepository boardRepository, ModelMapper modelMapper, JwtConfig jwtConfig) {
        this.boardRepository = boardRepository;
        this.schoolRepository = schoolRepository;
        this.modelMapper = modelMapper;
        this.jwtConfig = jwtConfig;
        this.userRepository = userRepository;
        this.boardTypeRepository = boardTypeRepository;
    }

    public BoardResponseDTO createBoard(BoardRequestDTO boardRequestDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        Board board = modelMapper.map(boardRequestDTO, Board.class);

        board.setSchool(schoolRepository.findById(String.valueOf(jwt.get("userSchool"))).get());
        board.setUser(userRepository.findById(String.valueOf(jwt.get("userId"))).get());
        board.setBoardType(boardTypeRepository.findById(boardRequestDTO.getBoardTypePK()).get());
        board.setCreatedAT(new Timestamp(System.currentTimeMillis()));
        board.setRecommendCount(0);
        board.setReportCount(0);
        board.setCommentCount(0);

        Board result = boardRepository.save(board);
        return modelMapper.map(result, BoardResponseDTO.class);
    }

    public Optional<BoardResponseDTO> boardGetByIdWithSchool(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        Optional<Board> boardEntity = boardRepository.findById(id);
        if (boardEntity.isPresent() && boardEntity.get().getBoardType().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            return Optional.of(modelMapper.map(boardEntity.get(), BoardResponseDTO.class));
        } else {
            return Optional.empty();
        }
    }

    public ArrayList<BoardResponseDTO> boardGetByBoardType(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        List<Board> boardEntities = boardRepository.findByBoardType_BoardTypePK(id);
        if (!boardEntities.isEmpty() && boardEntities.get(0).getBoardType().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            ArrayList<BoardResponseDTO> boardResponseDTOS = new ArrayList<>();
            for (Board board : boardEntities) {
                boardResponseDTOS.add(modelMapper.map(board, BoardResponseDTO.class));
            }
            return boardResponseDTOS;
        } else {
            return new ArrayList<BoardResponseDTO>();
        }

    }


    public BoardResponseDTO updateBoard(BoardResponseDTO boardResponseDTO) {
        Board board = modelMapper.map(boardResponseDTO, Board.class);
        Board result = boardRepository.save(board);
        return modelMapper.map(result, BoardResponseDTO.class);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
