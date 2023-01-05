package com.untouchable.everytime.Mapper;

import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Entity.SchoolEntity;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Enum.UserStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(value = PER_CLASS)
class UserDTOTest {
    ModelMapper modelMapper;

    @BeforeAll
    void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("UserEntity to UserDTO")
    public void UserDtoTOUserEntity() {
        UserDTO userDTO = UserDTO.builder()
                .User_ID("test")
                .PWD("test")
                .name("test")
                .nickname("Tester")
                .email("test@test.com")
                .schoolName("Dong-eui")
                .registeredYear(2020)
                .verified(true)
                .point(100L)
                .status(UserStatus.ACTIVE)
                .build();

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        assertEquals(userDTO.getUser_ID(), userEntity.getUserID());
        assertEquals(userDTO.getPWD(), userEntity.getPWD());
        assertEquals(userDTO.getName(), userEntity.getName());
        assertEquals(userDTO.getNickname(), userEntity.getNickname());
        assertEquals(userDTO.getEmail(), userEntity.getEmail());
        assertEquals(userDTO.getSchoolName(), userEntity.getSchool().getSchoolName());
        assertEquals(userDTO.getRegisteredYear(), userEntity.getRegisteredYear());
        assertEquals(userDTO.isVerified(), userEntity.isVerified());
        assertEquals(userDTO.getPoint(), userEntity.getPoint());
        assertEquals(userDTO.getStatus(), userEntity.getStatus());
    }

    @Test
    @DisplayName("UserEntity to UserDTO")
    public void UserEntityToUserDTO() {
        UserEntity userEntity = UserEntity.builder()
                .UserID("test")
                .PWD("test")
                .name("test1")
                .nickname("Tester")
                .email("test@test.com")
                .school(SchoolEntity.builder().schoolName("dong-eui").build())
                .registeredYear(2020)
                .verified(true)
                .point(100L)
                .status(UserStatus.ACTIVE)
                .build();
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        assertEquals(userDTO.getPWD(), userEntity.getPWD());
        assertEquals(userDTO.getName(), userEntity.getName());
        assertEquals(userDTO.getNickname(), userEntity.getNickname());
        assertEquals(userDTO.getEmail(), userEntity.getEmail());
        assertEquals(userDTO.getSchoolName(), userEntity.getSchool().getSchoolName());
        assertEquals(userDTO.getRegisteredYear(), userEntity.getRegisteredYear());
        assertEquals(userDTO.isVerified(), userEntity.isVerified());
        assertEquals(userDTO.getPoint(), userEntity.getPoint());
        assertEquals(userDTO.getStatus(), userEntity.getStatus());


    }
}