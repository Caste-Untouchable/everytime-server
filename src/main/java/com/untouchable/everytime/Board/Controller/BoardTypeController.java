package com.untouchable.everytime.Board.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Board.DTO.BoardTypeDTO;
import com.untouchable.everytime.Board.Service.BoardTypeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Tag(name = "게시글 종류 CRUD", description = "게시글 종류 CRUD 관련 API")
@RestController
@RequestMapping("/boardType")
public class BoardTypeController {

    BoardTypeService boardTypeService;
    JwtConfig jwtConfig;

    @Autowired
    public BoardTypeController(BoardTypeService boardTypeService, JwtConfig jwtConfig) {
        this.boardTypeService = boardTypeService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/create")
    public BoardTypeDTO createBoardType(@RequestBody BoardTypeDTO boardTypeDTO) {
        return boardTypeService.createBoardType(boardTypeDTO);
    }

    @GetMapping("/getBoardTypeBySchoolName")
    public ResponseEntity<ArrayList<BoardTypeDTO>> getBoardType(
            @Parameter(name = "jwt", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {

        return boardTypeService.getBoardTypeBySchoolName(token);
    }

    @PatchMapping("/update")
    public BoardTypeDTO updateBoardType(@RequestBody BoardTypeDTO boardTypeDTO) {
        return boardTypeService.createBoardType(boardTypeDTO);
    }

    @DeleteMapping("/delete")
    public void deleteBoardType(@RequestBody BoardTypeDTO boardTypeDTO) {
        boardTypeService.deleteBoardType(boardTypeDTO.getBoardTypePK());
    }


}
