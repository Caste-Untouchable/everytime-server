package com.untouchable.everytime.Controller;

import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Enum.AttachmentType;
import com.untouchable.everytime.Service.UserService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO userDTO) {
        // TODO : 해야함
        UserDTO user = userDTO;
        userService.login(user.getID(), user.getPWD());
        return user;
    }
    @PostMapping("/singup")
    public UserDTO singup(@RequestBody UserDTO userDTO) {

        return userService.register(userDTO);
    }

    @PatchMapping("/update")
    public UserDTO update() {
        // TODO : 해야함
        UserDTO user = new UserDTO();
        return user;
    }
    @DeleteMapping("/delete")
    public UserDTO delete() {
        // TODO : 해야함
        UserDTO user = new UserDTO();
        return user;
    }



}
