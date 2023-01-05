package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.BoardCommentDTO;
import com.untouchable.everytime.Entity.BoardCommentEntity;
import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Repository.BoardCommentRepository;
import com.untouchable.everytime.Repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardCommentService {

    BoardCommentRepository boardCommentRepository;
    BoardRepository boardRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;

    @Autowired
    public BoardCommentService(BoardRepository boardRepository, BoardCommentRepository boardCommentRepository, ModelMapper modelMapper, JwtConfig jwtConfig) {
        this.boardCommentRepository = boardCommentRepository;
        this.modelMapper = modelMapper;
        this.jwtConfig = jwtConfig;
        this.boardRepository = boardRepository;
    }

    public ResponseEntity<BoardCommentDTO> createBoardComment(BoardCommentDTO boardCommentDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        BoardCommentEntity resultEntity = boardCommentRepository.save(modelMapper.map(boardCommentDTO, BoardCommentEntity.class));

        if (resultEntity.getBoard().getSchool().getSchoolName().equals(jwt.get("schoolName"))) {
            return ResponseEntity.ok(modelMapper.map(resultEntity, BoardCommentDTO.class));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<BoardCommentDTO> updateBoardComment(BoardCommentDTO boardCommentDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        BoardCommentEntity resultEntity = modelMapper.map(boardCommentDTO, BoardCommentEntity.class);

        if (resultEntity.getBoard().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            return ResponseEntity.ok(modelMapper.map(boardCommentRepository.save(resultEntity), BoardCommentDTO.class));

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public boolean deleteBoardComment(Long id, String token) {
        Optional<BoardCommentEntity> found = boardCommentRepository.findById(id);
        if (found.isPresent() && found.get().getUser().getUserID().equals(jwtConfig.verifyJWT(token).get("userID"))) {
            boardCommentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    public ResponseEntity<ArrayList<BoardCommentDTO>> getBoardCommentByBoardID(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);

        if (boardEntity.isPresent() && boardEntity.get().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            List<BoardCommentEntity> result = boardCommentRepository.findByBoard_BoardPK(id);

            ArrayList<BoardCommentDTO> resultDTO = new ArrayList<>();

            for (BoardCommentEntity boardCommentEntity : result) {
                resultDTO.add(modelMapper.map(boardCommentEntity, BoardCommentDTO.class));
            }

            return ResponseEntity.ok(resultDTO);

        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}

