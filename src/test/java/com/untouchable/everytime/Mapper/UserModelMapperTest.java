package com.untouchable.everytime.Mapper;

import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Enum.UserStatus;
import com.untouchable.everytime.Repository.SchoolRepository;
import com.untouchable.everytime.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(value = PER_CLASS)
@RunWith(MockitoJUnitRunner.class)
class UserModelMapperTest {

    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SchoolRepository schoolRepository;

    @BeforeEach
    public void setup() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    @DisplayName("UserDTO to UserEntity  ")
    public void UserDtoTOUserEntity() {
        UserDTO userDTO = UserDTO.builder()
                .userId("test_ID")
                .userPwd("test_PWD")
                .userName("test_NAME")
                .userNickname("test_NICKNAME")
                .userEmail("test@test.com")
                //.userSchoolSchoolName("Dong-eui")
                .userRegisteredYear(2020)
                .userSchoolVerified(true)
                .userPoint(100L)
                .userStatus(UserStatus.ACTIVE)
                .build();

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        assertEquals(userDTO.getUserId(), userEntity.getUserId());
        assertEquals(userDTO.getUserPwd(), userEntity.getUserPwd());
        assertEquals(userDTO.getUserName(), userEntity.getUserName());
        assertEquals(userDTO.getUserNickname(), userEntity.getUserNickname());
        assertEquals(userDTO.getUserEmail(), userEntity.getUserEmail());
        //assertEquals(userDTO.getUserSchoolSchoolName(), userEntity.getUserSchool().getSchoolName());
        assertEquals(userDTO.getUserRegisteredYear(), userEntity.getUserRegisteredYear());
        assertEquals(userDTO.isUserSchoolVerified(), userEntity.isUserSchoolVerified());
        assertEquals(userDTO.getUserPoint(), userEntity.getUserPoint());
        assertEquals(userDTO.getUserStatus(), userEntity.getUserStatus());
    }

    @Test
    @DisplayName("UserEntity to UserDTO")
    public void UserEntityToUserDTO() {
        UserEntity userEntity = UserEntity.builder()
                .userId("test_ID")
                .userPwd("test_PWD")
                .userName("test_NAME")
                .userNickname("test_NICKNAME")
                .userEmail("test@test.com")
                //.userSchoolSchoolName("Dong-eui")
                .userRegisteredYear(2020)
                .userSchoolVerified(true)
                .userPoint(100L)
                .userStatus(UserStatus.ACTIVE)
                .build();

        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        assertEquals(userDTO.getUserPwd(), userEntity.getUserPwd());
        assertEquals(userDTO.getUserName(), userEntity.getUserName());
        assertEquals(userDTO.getUserNickname(), userEntity.getUserNickname());
        assertEquals(userDTO.getUserEmail(), userEntity.getUserEmail());
        //assertEquals(userDTO.getUserSchoolSchoolName(), userEntity.getUserSchool().getSchoolName());
        assertEquals(userDTO.getUserRegisteredYear(), userEntity.getUserRegisteredYear());
        assertEquals(userDTO.isUserSchoolVerified(), userEntity.isUserSchoolVerified());
        assertEquals(userDTO.getUserPoint(), userEntity.getUserPoint());
        assertEquals(userDTO.getUserStatus(), userEntity.getUserStatus());


    }
}
