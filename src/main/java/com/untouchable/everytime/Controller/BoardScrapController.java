package com.untouchable.everytime.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.BoardScrapDTO;
import com.untouchable.everytime.Service.BoardScrapService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Tag(name = "게시글 scrap", description = "")
@RestController
@RequestMapping("/boardScrap")
public class BoardScrapController {

    BoardScrapService boardScarpService;
    JwtConfig jwtConfig;

    @Autowired
    public BoardScrapController(BoardScrapService boardScarpService, JwtConfig jwtConfig) {
        this.boardScarpService = boardScarpService;
        this.jwtConfig = jwtConfig;
    }

    @GetMapping("/getMyScrap")
    public ArrayList<BoardScrapDTO> getMyScrap(@RequestHeader(value = "jwt") String token) {
        return boardScarpService.getMyScrap(token);
    }



}
