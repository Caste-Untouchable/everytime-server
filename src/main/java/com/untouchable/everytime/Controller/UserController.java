package com.untouchable.everytime.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Tag(name = "User CRUD", description = "유저 정보 CRUD 관련 API")
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
    @ResponseBody
    public String login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO.getUser_ID(), userDTO.getPWD());

    }

    @PostMapping("/signup")
    public UserDTO singup(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PatchMapping("/update")
    public UserDTO update(
            @RequestHeader(value = "jwt") String token,
            @RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserDTO> delete(@RequestHeader(value = "jwt") String token) {
        Map<String, Object> result = jwtConfig.verifyJWT(token);
        userService.deleteUser((String) result.get("ID"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> info(@RequestHeader(value = "jwt") String token) {
        Map<String, Object> result = jwtConfig.verifyJWT(token);
        Optional<UserDTO> user = userService.getUserById((String) result.get("ID"));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
