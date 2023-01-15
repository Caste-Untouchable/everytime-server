package com.untouchable.everytime.Repository;

import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Enum.UserStatus;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SchoolRepository schoolRepository;
    

    @BeforeAll
    void setUp() {
        schoolRepository.save(
                School.builder()
                        .schoolName("한국대학교")
                        .build()
        );
    }

    @Test
    @DisplayName("유저 회원가입")
    void createUser(){
        // given
        User user = User.builder()
                .userId("testUserID")
                .userPwd("testUserPwd")
                .userEmail("testUserEmail")
                .userName("testUserName")
                .userNickname("testUserNickname")
                .userStatus(UserStatus.ACTIVE)
                .userPoint(0L)
                .userRegisteredYear(2019)
                .userSchool(School.builder().schoolName("한국대학교").build())
                .build();

        // when
        User savedUser = userRepository.save(user);

        //then
        Assertions.assertEquals(user.getUserId(), savedUser.getUserId());

    }

    @Test
    void existsByUserId() {
    }
}
