package com.untouchable.everytime.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Service.BoardReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boardReport")
public class BoardReportController {

    BoardReportService boardReportService;
    JwtConfig jwtConfig;
}
