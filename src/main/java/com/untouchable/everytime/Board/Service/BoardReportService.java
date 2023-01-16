package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardReport;
import com.untouchable.everytime.Board.Enum.ReportType;
import com.untouchable.everytime.Board.Repository.BoardReportRepository;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.User.Repository.UserRepository;
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

    public ResponseEntity<String> reportBoard(Long id, String token, String content) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<Board> boardEntity = boardRepository.findById(id);

        if (boardEntity.isPresent() && boardEntity.get().getSchool().getSchoolName().equals(jwt.get("SCHOOL"))) {
            // 신고 히스토리 저장
            BoardReport boardReport = new BoardReport();
            boardReport.setReportBoard(boardEntity.get());
            boardReport.setReportUser(userRepository.findById(String.valueOf(jwt.get("ID"))).get());
            boardReport.setReportType(ReportType.valueOf(content));
            boardReportRepository.save(boardReport);

            // 신고 횟수 증가
            boardEntity.get().setReportCount(boardEntity.get().getReportCount() + 1);
            boardRepository.save(boardEntity.get());

            return ResponseEntity.ok().body("신고 완료");
        } else {
            return ResponseEntity.badRequest().body("이미 신고 했습니다.");
        }
    }
}
