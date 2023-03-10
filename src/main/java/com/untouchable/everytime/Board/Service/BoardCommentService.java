package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Board.DTO.BoardRequestDTO;
import com.untouchable.everytime.Board.Entity.BoardCommentRecommend;
import com.untouchable.everytime.Board.Entity.BoardCommentReport;
import com.untouchable.everytime.Board.Enum.ReportType;
import com.untouchable.everytime.Board.Repository.BoardCommentRecommendRepository;
import com.untouchable.everytime.Board.Repository.BoardCommentReportRepository;
import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.DTO.BoardCommentResponseDTO;
import com.untouchable.everytime.Board.Entity.BoardComment;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.Board.Repository.BoardCommentRepository;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.User.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
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
    BoardCommentRecommendRepository boardCommentRecommendRepository;
    BoardCommentReportRepository boardCommentReportRepository;
    BoardRepository boardRepository;
    UserRepository userRepository;
    SchoolRepository schoolRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;

    @Autowired
    public BoardCommentService(BoardCommentReportRepository boardCommentReportRepository, BoardCommentRecommendRepository boardCommentRecommendRepository, SchoolRepository schoolRepository, UserRepository userRepository, BoardRepository boardRepository, BoardCommentRepository boardCommentRepository, ModelMapper strictMapper, JwtConfig jwtConfig) {
        this.boardCommentRepository = boardCommentRepository;
        this.boardCommentReportRepository = boardCommentReportRepository;
        this.modelMapper = strictMapper;
        this.jwtConfig = jwtConfig;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.boardCommentRecommendRepository = boardCommentRecommendRepository;
    }

    public ResponseEntity<BoardCommentResponseDTO> addBoardComment(BoardRequestDTO boardRequestDTO, Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        // Null check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (boardEntity.isEmpty() || userEntity.isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        BoardComment boardComment = modelMapper.map(boardRequestDTO, BoardComment.class);
        boardComment.setBoard(boardEntity.get());
        boardComment.setUser(userEntity.get());
        boardComment.setCreatedAT(new Timestamp(System.currentTimeMillis()));
        boardComment.setReportCount(0L);
        boardComment.setRecommendCount(0L);

        return ResponseEntity.ok(modelMapper.map(boardCommentRepository.save(boardComment), BoardCommentResponseDTO.class));
    }

    public ResponseEntity<ArrayList<BoardCommentResponseDTO>> findCommentByBoard(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        // Null check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        Optional<School> schoolEntity = schoolRepository.findById(String.valueOf(jwt.get("userSchool")));
        if (boardEntity.isEmpty() || userEntity.isEmpty() || schoolEntity.isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        // ?????? ??????
        if (!boardEntity.get().getSchool().equals(schoolEntity.get())) {
            ResponseEntity.badRequest().build();
        }

        List<BoardComment> result = boardCommentRepository.findByBoard_BoardPk(id);

        ArrayList<BoardCommentResponseDTO> resultDTO = new ArrayList<>();

        for (BoardComment boardComment : result) {
            BoardCommentResponseDTO boardCommentResponseDTO = modelMapper.map(boardComment, BoardCommentResponseDTO.class);
            if (boardCommentResponseDTO.isAnonymity()) {
                boardCommentResponseDTO.setUserUserID(null);
                boardCommentResponseDTO.setUserUserNickname(null);
            }
            resultDTO.add(boardCommentResponseDTO);
        }

        return ResponseEntity.ok(resultDTO);

    }

    public ResponseEntity<BoardCommentResponseDTO> updateBoardComment(BoardRequestDTO boardRequestDTO, Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardComment> boardCommentEntity = boardCommentRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (boardCommentEntity.isEmpty() || userEntity.isEmpty()) {
            ResponseEntity.badRequest().build();
        }

        // ????????? ??????
        if (!boardCommentEntity.get().getUser().equals(userEntity.get())) {
            ResponseEntity.badRequest().build();
        }

        // ??????
        BoardComment boardComment = boardCommentEntity.get();
        boardComment.setContent(boardRequestDTO.getContent());

        return ResponseEntity.ok(modelMapper.map(boardCommentRepository.save(boardComment), BoardCommentResponseDTO.class));
    }

    public ResponseEntity<String> deleteBoardComment(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardComment> boardCommentEntity = boardCommentRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (boardCommentEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ????????? ??????
        if (!boardCommentEntity.get().getUser().equals(userEntity.get())) {
            return ResponseEntity.badRequest().build();
        }

        boardCommentRepository.deleteById(id);

        return ResponseEntity.ok("?????????????????????.");
    }

    public ResponseEntity<String> addCommentRecommend(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardComment> boardCommentEntity = boardCommentRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (boardCommentEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ?????? ??????
        if (boardCommentRecommendRepository.existsByBoardCommentAndUser(boardCommentEntity.get(), userEntity.get())) {
            return ResponseEntity.badRequest().body("?????? ?????????????????????.");
        }
        // ?????? ??????
        BoardCommentRecommend boardCommentRecommend = new BoardCommentRecommend();
        boardCommentRecommend.setBoardComment(boardCommentEntity.get());
        boardCommentRecommend.setUser(userEntity.get());
        boardCommentRecommendRepository.save(boardCommentRecommend);

        // ?????? ??? ?????????
        BoardComment boardComment = boardCommentEntity.get();
        boardComment.setRecommendCount(boardCommentRecommendRepository.countByBoardComment(boardComment));
        boardCommentRepository.save(boardComment);

        return ResponseEntity.ok("?????????????????????.");
    }

    public ResponseEntity<String> reportCommend(Long id, String token, ReportType reportType) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardComment> boardCommentEntity = boardCommentRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (boardCommentEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ?????? ??????
        if (boardCommentReportRepository.existsByReportUserAndReportBoardComment(userEntity.get(), boardCommentEntity.get())) {
            return ResponseEntity.badRequest().body("?????? ?????????????????????.");
        }

        // ??????
        BoardCommentReport boardCommentReport = new BoardCommentReport();
        boardCommentReport.setReportUser(userEntity.get());
        boardCommentReport.setReportBoardComment(boardCommentEntity.get());
        boardCommentReport.setReportType(reportType);


        boardCommentReportRepository.save(boardCommentReport);

        // ?????? ??? ?????????
        BoardComment boardComment = boardCommentEntity.get();
        boardComment.setReportCount(boardCommentReportRepository.countByReportBoardComment(boardComment));
        boardCommentRepository.save(boardComment);

        return ResponseEntity.ok("?????????????????????.");
    }


}

