package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardReport;
import com.untouchable.everytime.Board.Enum.ReportType;
import com.untouchable.everytime.Board.Repository.BoardReportRepository;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.Entity.User;
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
    SchoolRepository schoolRepository;
    BoardRepository boardRepository;
    JwtConfig jwtConfig;
    ModelMapper modelMapper;

    @Autowired
    public BoardReportService(SchoolRepository schoolRepository,UserRepository userRepository, BoardRepository boardRepository, BoardReportRepository boardReportRepository, JwtConfig jwtConfig, ModelMapper modelMapper) {
        this.boardReportRepository = boardReportRepository;
        this.jwtConfig = jwtConfig;
        this.modelMapper = modelMapper;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
    }

    public ResponseEntity<String> reportBoard(Long id, String token, ReportType report) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        // Null Check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        Optional<School> schoolEntity = schoolRepository.findById(String.valueOf(jwt.get("userSchool")));
        if (boardEntity.isEmpty() || userEntity.isEmpty() || schoolEntity.isEmpty()) {
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }

        // 신고자 학교가 같은지 확인
        if (!boardEntity.get().getSchool().equals(userEntity.get().getUserSchool())){
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }

        // 신고 중복 확인
        if (boardReportRepository.existsByReportUserAndReportBoard(userEntity.get(), boardEntity.get())) {
            return ResponseEntity.badRequest().body("이미 신고 했습니다.");
        }

        // 신고 히스토리 저장
        BoardReport boardReport = new BoardReport();
        boardReport.setReportBoard(boardEntity.get());
        boardReport.setReportUser(userEntity.get());
        boardReport.setReportType(report);
        boardReportRepository.save(boardReport);

        // 신고 카운트 최신화
        Long reportCount = boardReportRepository.countByReportBoard(boardEntity.get());
        boardEntity.get().setReportCount(reportCount);
        boardRepository.save(boardEntity.get());


        return ResponseEntity.ok("신고가 완료되었습니다.");
    }
}
