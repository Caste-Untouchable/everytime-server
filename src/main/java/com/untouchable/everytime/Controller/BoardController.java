package com.untouchable.everytime.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.BoardDTO;
import com.untouchable.everytime.Service.BoardReportService;
import com.untouchable.everytime.Service.BoardScrapService;
import com.untouchable.everytime.Service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Tag(name = "게시글 CRUD", description = "게시글 CRUD 관련 API")
@RestController
@RequestMapping("/board")
public class BoardController {

    BoardScrapService boardScrapService;
    BoardReportService boardReportService;
    BoardService boardService;
    JwtConfig jwtConfig;

    @Autowired
    public BoardController(BoardScrapService boardScrapService,BoardReportService boardReportService, BoardService boardService, JwtConfig jwtConfig) {
        this.boardService = boardService;
        this.jwtConfig = jwtConfig;
        this.boardReportService = boardReportService;
        this.boardScrapService = boardScrapService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable("id") Long id, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardDTO> result = boardService.boardGetByIdWithSchool(id, token);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/scrap/{id}")
    public ResponseEntity scrapBoard(@PathVariable("id") Long id, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return scrapBoard(id, token);
    }

    @Tag(name = "신고는 body에 담아주세요", description = "ABUSING, SCAM, COMMERCIAL, BELITTLE, PORNO, PHISHING, INAPPROPRIATE")
    @PostMapping("/report/{id}")
    public ResponseEntity reportBoard(@PathVariable("id") Long id, @RequestHeader(value = "jwt") String token,@RequestBody String content) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardReportService.reportBoard(id, token,content);
    }

    @GetMapping("/getBoardByBoardType/{boardTypeId}")
    public ResponseEntity<ArrayList<BoardDTO>> getBoardByBoardType(@PathVariable("boardTypeId") Long boardTypeId, @RequestHeader(value = "jwt") String token) {
        //그냥 검증용 코드
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        ArrayList<BoardDTO> result = boardService.boardGetByBoardType(boardTypeId, token);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);


        return ResponseEntity.ok(boardService.createBoard(boardDTO,token));
    }

    @PatchMapping("/update")
    public ResponseEntity<BoardDTO> updateBoard(@RequestBody BoardDTO boardDTO, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        return ResponseEntity.ok(boardService.updateBoard(boardDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBoard(@RequestParam("id") Long id, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardDTO> result = boardService.boardGetByIdWithSchool(id, token);

        if (result.isPresent() && result.get().getUserID().equals(jwt.get("USERID"))) {
            boardService.deleteBoard(id);
            return ResponseEntity.ok("삭제 성공");
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}

