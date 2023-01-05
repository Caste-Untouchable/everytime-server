package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Entity.BoardReportEntity;
import com.untouchable.everytime.Enum.ReportType;
import com.untouchable.everytime.Repository.BoardReportRepository;
import com.untouchable.everytime.Repository.BoardRepository;
import com.untouchable.everytime.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BoardReportService {
    BoardReportRepository boardReportRepository;
    UserRepository userRepository;
    BoardRepository boardRepository;
    JwtConfig jwtConfig;
    ModelMapper modelMapper;

    @Autowired
    public BoardReportService(UserRepository userRepository, BoardRepository boardRepository, BoardReportRepository boardReportRepository, JwtConfig jwtConfig, ModelMapper modelMapper) {
        this.boardReportRepository = boardReportRepository;
        this.jwtConfig = jwtConfig;
        this.modelMapper = modelMapper;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity reportBoard(Long id, String token, String content) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardEntity> boardEntity = boardRepository.findById(id);

        if (boardEntity.isPresent() && boardEntity.get().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            // 신고 히스토리 저장
            BoardReportEntity boardReportEntity = new BoardReportEntity();
            boardReportEntity.setReportBoard(boardEntity.get());
            boardReportEntity.setReportUser(userRepository.findById(String.valueOf(jwt.get("ID"))).get());
            boardReportEntity.setReportType(ReportType.valueOf(content));
            boardReportRepository.save(boardReportEntity);

            // 신고 횟수 증가
            boardEntity.get().setReportCount(boardEntity.get().getReportCount() + 1);
            boardRepository.save(boardEntity.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
