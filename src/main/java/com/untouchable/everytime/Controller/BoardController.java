package com.untouchable.everytime.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.BoardDTO;
import com.untouchable.everytime.DTO.BoardScrapDTO;
import com.untouchable.everytime.Service.BoardReportService;
import com.untouchable.everytime.Service.BoardScrapService;
import com.untouchable.everytime.Service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Tag(name = "게시글", description = "게시글 CRUD 관련 API")
@RestController
@RequestMapping("/board")
public class BoardController {
    BoardScrapService boardScrapService;
    BoardReportService boardReportService;
    BoardService boardService;
    JwtConfig jwtConfig;

    @Autowired
    public BoardController(BoardScrapService boardScrapService, BoardReportService boardReportService, BoardService boardService, JwtConfig jwtConfig) {
        this.boardService = boardService;
        this.jwtConfig = jwtConfig;
        this.boardReportService = boardReportService;
        this.boardScrapService = boardScrapService;
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "게시글 조회", description = "게시글 PK와, JWT를 입력받아 회원 인증 후 특정 게시물 조회 하는 기능")
    public ResponseEntity<BoardDTO> getBoard(
            @Parameter(name = "게시글 PK", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardDTO> result = boardService.boardGetByIdWithSchool(id, token);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{id}/report")
    @Operation(summary = "특정 게시물 신고", description = "Body에는 다음 ABUSING, SCAM, COMMERCIAL, BELITTLE, PORNO, PHISHING, INAPPROPRIATE 문자열만 넣어주세요")
    public ResponseEntity<String> reportBoard(
            @Parameter(name = "게시글 PK", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 인증 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token,
            @RequestBody String content) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardReportService.reportBoard(id, token, content);
    }

    @GetMapping("/getBoardByBoardType/{boardTypeId}")
    @Operation(summary = "특정 게시판들 글만 조회", description = "ex)동의대학교 자유게시판 조회")
    public ResponseEntity<ArrayList<BoardDTO>> getBoardByBoardType(
            @Parameter(name = "게시글 PK", description = "특정 게시판 PK", in = ParameterIn.PATH) @PathVariable("boardTypeId") Long boardTypeId,
            @Parameter(name = "jwt", description = "유저 인증 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {

        ArrayList<BoardDTO> result = boardService.boardGetByBoardType(boardTypeId, token);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    @Operation(summary = "게시글 생성", description = "게시글 새로 생성하는 기능")
    public ResponseEntity<BoardDTO> createBoard(
            @RequestBody BoardDTO boardDTO,
            @Parameter(name = "jwt", description = "유저 인증 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {
        return ResponseEntity.ok(boardService.createBoard(boardDTO, token));
    }

    @PatchMapping("/update")
    @Operation(summary = "게시글 수정", description = "게시글 수정 하는 기능")
    public ResponseEntity<BoardDTO> updateBoard(
            @RequestBody BoardDTO boardDTO,
            @Parameter(name = "jwt", description = "유저 인증 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        return ResponseEntity.ok(boardService.updateBoard(boardDTO));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "게시글 삭제", description = "게시글 삭제하는 기능")
    public ResponseEntity<String> deleteBoard(
            @Parameter(name = "게시글 PK", description = "게시글 PK") @RequestParam("id") Long id,
            @Parameter(name = "jwt", description = "유저 인증 토큰") @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<BoardDTO> result = boardService.boardGetByIdWithSchool(id, token);

        if (result.isPresent() && result.get().getUserID().equals(jwt.get("USERID"))) {
            boardService.deleteBoard(id);
            return ResponseEntity.ok("삭제 성공");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/{id}/scrap")
    @Operation(summary = "게시글 스크랩", description = "게시글 스크랩 하는 기능")
    public ResponseEntity scrapBoard(
            @Parameter(name = "게시글 PK", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 인증 토큰") @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return scrapBoard(id, token);
    }

    @GetMapping("/getMyScrap")
    @Operation(summary = "스크랩한 글 조회", description = "스크랩 한 글들을 조회하는 기능")
    public ArrayList<BoardScrapDTO> getMyScrap(
            @Parameter(name = "jwt", description = "유저 인증 토큰") @RequestHeader(value = "jwt") String token) {
        return boardScrapService.getMyScrap(token);
    }
}

