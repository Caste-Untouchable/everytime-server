package com.untouchable.everytime.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자", description = "유저 정보 CRUD 관련 API")
@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    JwtConfig jwtConfig;

    @Autowired
    public UserController(UserService userService, JwtConfig jwtConfig) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "ID, PWD를 입력받아 JWT 발급하는 기능")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO.getUserId(), userDTO.getUserPwd());
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "유저 정보를 입력받아 회원가입 하는 기능")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "ID가 중복입니다."),
            @ApiResponse(responseCode = "200", description = "회원가입 성공")
    })
    public ResponseEntity<UserDTO> singup(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PatchMapping("/update")
    @Operation(summary = "회원 정보 수정", description = "유저 정보를 입력받아 수정하는 기능")
    public ResponseEntity<UserDTO> update(
            @Parameter(name = "JWT", description = "유저 토큰") @RequestHeader(value = "jwt") String token,
            @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO, token);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 하는 기능")
    public ResponseEntity<String> delete(
            @Parameter(name = "JWT", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return userService.deleteUser(token);
    }

    @Operation(summary = "User Info 조회", description = "JWT로 인증 후, 해당하는 유저 정보 조회")
    @ApiResponse(responseCode = "200", description = "유저 정보 가져오기 성공")
    @GetMapping("/info")
    public ResponseEntity<UserDTO> info(
            @Parameter(name = "JWT", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return userService.getUserById(token);
    }
}
