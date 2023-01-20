package com.untouchable.everytime.Board.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.DTO.BoardCommentDTO;
import com.untouchable.everytime.Board.Service.BoardCommentService;
import com.untouchable.everytime.Board.Service.BoardRecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Tag(name = "댓글", description = "게시글 댓글 관련 API")
@RestController
@RequestMapping("/boardComment")
public class BoardCommentController {

    BoardCommentService boardCommentService;
    BoardRecommendService boardRecommendService;
    JwtConfig jwtConfig;

    @Autowired
    public BoardCommentController(BoardCommentService boardCommentService, JwtConfig jwtConfig, BoardRecommendService boardRecommendService) {
        this.boardCommentService = boardCommentService;
        this.jwtConfig = jwtConfig;
        this.boardRecommendService = boardRecommendService;
    }

    @GetMapping("/Board/{id}")
    @Operation(summary = "특정 게시글 댓글 조회", description = "해당 게시글 조회 후, JWT 에 포함된 학교와 일치 할 시 해당 게시글 댓글 리스트를 반환")
    public ResponseEntity<ArrayList<BoardCommentDTO>> getBoardCommentByBoardId(
            @Parameter(name = "id", description = "게시글 번호", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "JWT", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        //그냥 검증용 코드
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardCommentService.getBoardCommentByBoardID(id, token);
    }

    @PostMapping("/recommend/{id}")
    @Operation(summary = "특정 댓글 공감하기", description = "해당 댓글 공감하는 기능")
    public ResponseEntity<String> recommendBoardComment(
            @Parameter(name = "Comment PK", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "JWT", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardRecommendService.recommendBoardComment(id, token);
    }

    @PostMapping("/create")
    @Operation(summary = "댓글 달기", description = "게시글에 댓글 작성하는 기능")
    public ResponseEntity<BoardCommentDTO> createBoardComment(
            @RequestBody BoardCommentDTO boardCommentDTO,
            @Parameter(name = "JWT", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return boardCommentService.createBoardComment(boardCommentDTO, token);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "댓글 삭제", description = "특정 댓글을 삭제하는 기능")
    public ResponseEntity deleteBoardComment(
            @Parameter(name = "Comment PK", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "JWT", description = "유저 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        if (boardCommentService.deleteBoardComment(id, token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/update")
    @Operation(summary = "댓글 수정", description = "특정 댓글을 수정하는 기능")
    public ResponseEntity<BoardCommentDTO> updateBoardComment(
            @RequestBody BoardCommentDTO boardCommentDTO,
            @Parameter(name = "JWT", description = "유저 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardCommentService.updateBoardComment(boardCommentDTO, token);
    }
}
