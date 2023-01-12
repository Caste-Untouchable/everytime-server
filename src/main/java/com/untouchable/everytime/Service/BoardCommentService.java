package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.BoardCommentDTO;
import com.untouchable.everytime.Entity.BoardCommentEntity;
import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Repository.BoardCommentRepository;
import com.untouchable.everytime.Repository.BoardRepository;
import com.untouchable.everytime.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardCommentService {

    BoardCommentRepository boardCommentRepository;
    BoardRepository boardRepository;

    UserRepository userRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;

    @Autowired
    public BoardCommentService(UserRepository userRepository,BoardRepository boardRepository, BoardCommentRepository boardCommentRepository, ModelMapper strictMapper, JwtConfig jwtConfig) {
        this.boardCommentRepository = boardCommentRepository;
        this.modelMapper = strictMapper;
        this.jwtConfig = jwtConfig;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<BoardCommentDTO> createBoardComment(BoardCommentDTO boardCommentDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        BoardCommentEntity boardCommentEntity = modelMapper.map(boardCommentDTO, BoardCommentEntity.class);

        //게시글 유효한지 확인
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardCommentDTO.getBoardBoardPK());
        if (!boardEntity.isPresent()) {
            ResponseEntity.notFound().build();
        }

        // User 정보 매핑
        UserEntity userEntity = userRepository.findById(String.valueOf(jwt.get("ID"))).get();
        boardCommentEntity.setUser(userEntity);
        boardCommentEntity.setBoard(boardEntity.get());
        boardCommentEntity.setCreatedAT(new Timestamp(System.currentTimeMillis()));


        BoardCommentEntity resultEntity = boardCommentRepository.save(boardCommentEntity);

        return ResponseEntity.ok(modelMapper.map(resultEntity, BoardCommentDTO.class));
    }

    public ResponseEntity<BoardCommentDTO> updateBoardComment(BoardCommentDTO boardCommentDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardEntity> boardEntity = boardRepository.findById(boardCommentDTO.getBoardBoardPK());

        if (!boardEntity.isPresent()){
            ResponseEntity.notFound().build();
        } else if (!boardEntity.get().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            ResponseEntity.badRequest().build();
        }

        BoardCommentEntity resultEntity = boardCommentRepository.save(modelMapper.map(boardCommentDTO, BoardCommentEntity.class));

        return ResponseEntity.ok(modelMapper.map(resultEntity, BoardCommentDTO.class));
    }

    public boolean deleteBoardComment(Long id, String token) {
        Optional<BoardCommentEntity> found = boardCommentRepository.findById(id);
        if (found.isPresent() && found.get().getUser().getUserId().equals(jwtConfig.verifyJWT(token).get("userID"))) {
            boardCommentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    public ResponseEntity<ArrayList<BoardCommentDTO>> getBoardCommentByBoardID(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        //게시글 유효한지 확인
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        if (!boardEntity.isPresent()) {
            return ResponseEntity.notFound().build();
        }

//        //게시글 소속 학교가 토큰이랑 맞는지 확인
//        if (boardEntity.get().getSchool().getSchoolName().equals(String.valueOf(jwt.get("SCHOOL")))) {
//            return ResponseEntity.badRequest().build();
//        }

        List<BoardCommentEntity> result = boardCommentRepository.findByBoard_BoardPK(id);

        ArrayList<BoardCommentDTO> resultDTO = new ArrayList<>();

        for (BoardCommentEntity boardCommentEntity : result) {
            resultDTO.add(modelMapper.map(boardCommentEntity, BoardCommentDTO.class));
        }

        return ResponseEntity.ok(resultDTO);

    }

}

