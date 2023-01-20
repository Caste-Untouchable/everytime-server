package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.DTO.BoardCommentDTO;
import com.untouchable.everytime.Board.Entity.BoardComment;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.Board.Repository.BoardCommentRepository;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.User.Repository.UserRepository;
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
        BoardComment boardComment = modelMapper.map(boardCommentDTO, BoardComment.class);

        //게시글 유효한지 확인
        Optional<Board> boardEntity = boardRepository.findById(boardCommentDTO.getBoardBoardPK());
        if (!boardEntity.isPresent()) {
            ResponseEntity.notFound().build();
        }

        // User 정보 매핑
        User user = userRepository.findById(String.valueOf(jwt.get("ID"))).get();
        boardComment.setUser(user);
        boardComment.setBoard(boardEntity.get());
        boardComment.setCreatedAT(new Timestamp(System.currentTimeMillis()));


        BoardComment resultEntity = boardCommentRepository.save(boardComment);

        return ResponseEntity.ok(modelMapper.map(resultEntity, BoardCommentDTO.class));
    }

    public ResponseEntity<BoardCommentDTO> updateBoardComment(BoardCommentDTO boardCommentDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<Board> boardEntity = boardRepository.findById(boardCommentDTO.getBoardBoardPK());

        if (!boardEntity.isPresent()){
            ResponseEntity.notFound().build();
        } else if (!boardEntity.get().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            ResponseEntity.badRequest().build();
        }

        BoardComment resultEntity = boardCommentRepository.save(modelMapper.map(boardCommentDTO, BoardComment.class));

        return ResponseEntity.ok(modelMapper.map(resultEntity, BoardCommentDTO.class));
    }

    public boolean deleteBoardComment(Long id, String token) {
        Optional<BoardComment> found = boardCommentRepository.findById(id);
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
        Optional<Board> boardEntity = boardRepository.findById(id);
        if (!boardEntity.isPresent()) {
            return ResponseEntity.notFound().build();
        }

//        //게시글 소속 학교가 토큰이랑 맞는지 확인
//        if (boardEntity.get().getSchool().getSchoolName().equals(String.valueOf(jwt.get("SCHOOL")))) {
//            return ResponseEntity.badRequest().build();
//        }

        List<BoardComment> result = boardCommentRepository.findByBoard_BoardPk(id);

        ArrayList<BoardCommentDTO> resultDTO = new ArrayList<>();

        for (BoardComment boardComment : result) {
            resultDTO.add(modelMapper.map(boardComment, BoardCommentDTO.class));
        }

        return ResponseEntity.ok(resultDTO);

    }

}

