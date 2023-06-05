package com.untouchable.everytime.User.Entity;

import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.Enum.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    ModelMapper modelmapper = new ModelMapper();

    @Test
    @DisplayName("User Entity To UserDTO")
    void UserEntityToUserDTO(){
        User user = User.builder()
                .userId("test")
                .userPwd("test")
                .userName("test")
                .userNickname("test")
                .userEmail("test")
                .userSchool(School.builder().schoolName("test").build())
                .userRegisteredYear(2019)
                .userSchoolVerified(true)
                .userPoint(100L)
                .userStatus(UserStatus.ACTIVE).build();

        UserDTO userDTO = modelmapper.map(user, UserDTO.class);

        assertEquals(user.getUserId(), userDTO.getUserId());
        assertEquals(user.getUserPwd(), userDTO.getUserPwd());
        assertEquals(user.getUserName(), userDTO.getUserName());
        assertEquals(user.getUserNickname(), userDTO.getUserNickname());
        assertEquals(user.getUserEmail(), userDTO.getUserEmail());
        assertEquals(user.getUserSchool().getSchoolName(), userDTO.getUserSchoolSchoolName());
        assertEquals(user.getUserRegisteredYear(), userDTO.getUserRegisteredYear());
        assertEquals(user.isUserSchoolVerified(), userDTO.isUserSchoolVerified());
        assertEquals(user.getUserPoint(), userDTO.getUserPoint());
        assertEquals(user.getUserStatus(), userDTO.getUserStatus());
    }

    @Test
    @DisplayName("UserDTO To User Entity")
    void UserDTOToUserEntity(){
        UserDTO userDTO = UserDTO.builder()
                .userId("test")
                .userPwd("test")
                .userName("test")
                .userNickname("test")
                .userEmail("test")
                .userSchoolSchoolName("test")
                .userRegisteredYear(2019)
                .userSchoolVerified(true)
                .userPoint(100L)
                .userStatus(UserStatus.ACTIVE).build();

        User user = modelmapper.map(userDTO, User.class);

        assertEquals(userDTO.getUserId(), user.getUserId());
        assertEquals(userDTO.getUserPwd(), user.getUserPwd());
        assertEquals(userDTO.getUserName(), user.getUserName());
        assertEquals(userDTO.getUserNickname(), user.getUserNickname());
        assertEquals(userDTO.getUserEmail(), user.getUserEmail());
        assertEquals(userDTO.getUserSchoolSchoolName(), user.getUserSchool().getSchoolName());
        assertEquals(userDTO.getUserRegisteredYear(), user.getUserRegisteredYear());
        assertEquals(userDTO.isUserSchoolVerified(), user.isUserSchoolVerified());
        assertEquals(userDTO.getUserPoint(), user.getUserPoint());
        assertEquals(userDTO.getUserStatus(), user.getUserStatus());
    }
}