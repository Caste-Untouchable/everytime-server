package com.untouchable.everytime.Controller;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Enum.AttachmentType;
import com.untouchable.everytime.Service.UserService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저", description = "유저 정보 왔다갔다 하는 API")
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
        return userService.login(userDTO.getID(), userDTO.getPWD());

    }

    @PostMapping("/signup")
    public UserDTO singup(@RequestBody UserDTO userDTO) {

        return userService.register(userDTO);
    }

    @PatchMapping("/update")
    public UserDTO update(@RequestBody UserDTO userDTO) {
        // TODO : 해야함
        UserDTO user = new UserDTO();
        return user;
    }

    @DeleteMapping("/delete")
    public UserDTO delete(@RequestBody UserDTO userDTO) {
        // TODO : 해야함
        UserDTO user = new UserDTO();
        return user;
    }

    @GetMapping("/info")
    public UserDTO info(@RequestBody UserDTO userDTO) {
        // TODO : 해야함
        UserDTO user = new UserDTO();
        return user;
    }


}
