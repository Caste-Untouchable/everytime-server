package com.untouchable.everytime.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.BoardCommentDTO;
import com.untouchable.everytime.Entity.BoardCommentEntity;
import com.untouchable.everytime.Service.BoardCommentService;
import com.untouchable.everytime.Service.BoardRecommendService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Tag(name = "게시글 댓글 CRUD", description = "게시글 댓글 CRUD 관련 API")
@RestController
@RequestMapping("/boardComment")
public class BoardCommentController {

    BoardCommentService boardCommentService;
    BoardRecommendService boardRecommendService;
    JwtConfig jwtConfig;

    @Autowired
    public BoardCommentController(BoardCommentService boardCommentService, JwtConfig jwtConfig,BoardRecommendService boardRecommendService) {
        this.boardCommentService = boardCommentService;
        this.jwtConfig = jwtConfig;
        this.boardRecommendService = boardRecommendService;
    }


    @GetMapping("/Board/{id}")
    public ResponseEntity<ArrayList<BoardCommentDTO>> getBoardCommentByBoardId(@PathVariable Long id, @RequestHeader(value = "jwt") String token) {
        //그냥 검증용 코드
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardCommentService.getBoardCommentByBoardID(id, token);
    }

    @PostMapping("/recommend/{id}")
    public ResponseEntity<BoardCommentDTO> recommendBoardComment(@PathVariable Long id, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardRecommendService.recommendBoardComment(id, token);
    }

    @PostMapping("/create")
    public ResponseEntity<BoardCommentDTO> createBoardComment(@RequestBody BoardCommentDTO boardCommentDTO, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardCommentService.createBoardComment(boardCommentDTO, token);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBoardComment(@PathVariable Long id, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        if (boardCommentService.deleteBoardComment(id, token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PatchMapping("/update")
    public ResponseEntity<BoardCommentDTO> updateBoardComment(@RequestBody BoardCommentDTO boardCommentDTO, @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardCommentService.updateBoardComment(boardCommentDTO, token);
    }

}
