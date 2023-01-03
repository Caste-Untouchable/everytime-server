package com.untouchable.everytime.Controller;

import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Enum.AttachmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public UserDTO login() {
        // TODO : 해야함
        UserDTO user = new UserDTO();
        return user;
    }
    @PostMapping("/singup")
    public UserDTO singup() {
        // TODO : 해야함
        UserDTO user = new UserDTO();
        return user;
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
