package com.untouchable.everytime.User.Controller;

import com.untouchable.everytime.Board.Controller.BoardController;
import com.untouchable.everytime.Board.Controller.BoardTypeController;
import com.untouchable.everytime.Board.DTO.BoardRequestDTO;
import com.untouchable.everytime.Board.DTO.BoardResponseDTO;
import com.untouchable.everytime.Board.DTO.BoardTypeDTO;
import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.School.Controller.SchoolController;
import com.untouchable.everytime.School.DTO.SchoolInfoDTO;
import com.untouchable.everytime.User.DTO.UserChangePasswordDTO;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.DTO.UserLoginDTO;
import com.untouchable.everytime.User.Enum.UserStatus;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @Autowired
    private UserController userController;
    @Autowired
    private SchoolController schoolController;
    @Autowired
    private BoardController boardController;
    @Autowired
    private BoardTypeController boardTypeController;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtConfig jwtConfig;

    BoardTypeDTO boardTypeDTO;

    @BeforeAll
    void setUp() {
        SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
                .schoolName("Test University")
                .build();

        UserDTO userDTO = UserDTO.builder()
                .userId("testId")
                .userPwd("testPwd")
                .userName("testName")
                .userNickname("testNickname")
                .userEmail("testEmail@test.com")
                .userSchoolSchoolName("Test University")
                .userRegisteredYear(2019)
                .userProfile("")
                .userSchoolVerified(true)
                .userPoint(0L)
                .userStatus(UserStatus.ACTIVE)
                .build();

        BoardTypeDTO boardtypeDTO = BoardTypeDTO.builder()
                .boardType("testBoard")
                .schoolName("Test University")
                .build();

        schoolController.createSchool(schoolInfoDTO);
        boardTypeDTO = boardTypeController.createBoardType(boardtypeDTO);
        userController.singup(userDTO);
    }

    @Nested
    @DisplayName("로그인 테스트")
    class UserLogin {
        @Test
        @DisplayName("로그인 성공 케이스")
        void loginS() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .build();
            String token = userController.login(userLoginDTO).getBody();

            Map<String, Object> result = jwtConfig.verifyJWT(token);

            assertNotNull(token);

            assertEquals("testId", result.get("userId"));
            assertEquals("Test University", result.get("userSchool"));
            assertEquals("testNickname", result.get("userNickname"));
            assertEquals("testName", result.get("userName"));
        }

        @Test
        @DisplayName("로그인 실패 케이스")
        void loginF() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId111")
                    .userPwd("testPwd222")
                    .build();
            String token = userController.login(userLoginDTO).getBody();

            assertNull(token);
        }
    }

    @Nested
    @DisplayName("아이디 중복체크 확인")
    class IdDuplicateCheck {
        @Test
        @DisplayName("아이디 중복체크 실패 케이스")
        void idDuplicateCheckS() {
            String userId = "testId111";
            boolean result = userController.checkId(userId).getBody();
            assertTrue(result);
        }

        @Test
        @DisplayName("아이디 중복체크 성공 케이스")
        void idDuplicateCheckF() {
            String userId = "testId";
            boolean result = userController.checkId(userId).getBody();
            assertFalse(result);
        }
    }


    @Nested
    @DisplayName("회원가입 테스트")
    class SignUpTest {

        @Test
        @DisplayName("회원가입 성공 케이스")
        void singUpSuccess() {
            UserDTO userDTO = UserDTO.builder()
                    .userId("testId1")
                    .userPwd("testPwd1")
                    .userName("testName1")
                    .userNickname("testNickname1")
                    .userEmail("testEmail@test.com1")
                    .userSchoolSchoolName("Test University")
                    .userRegisteredYear(2019)
                    .userProfile("")
                    .userSchoolVerified(true)
                    .userPoint(100L)
                    .userStatus(UserStatus.ACTIVE)
                    .build();

            UserDTO result = userController.singup(userDTO).getBody();

            assertEquals(userDTO.getUserId(), result.getUserId());
            assertTrue(encoder.matches(userDTO.getUserPwd(), result.getUserPwd()));
            assertEquals(userDTO.getUserName(), result.getUserName());
            assertEquals(userDTO.getUserNickname(), result.getUserNickname());
            assertEquals(userDTO.getUserEmail(), result.getUserEmail());
            assertEquals(userDTO.getUserSchoolSchoolName(), result.getUserSchoolSchoolName());
            assertEquals(userDTO.getUserRegisteredYear(), result.getUserRegisteredYear());
            // assertEquals(userDTO.getUserProfile(), result.getUserProfile());
            assertEquals(userDTO.isUserSchoolVerified(), result.isUserSchoolVerified());
            assertEquals(0L, result.getUserPoint());
            assertEquals(userDTO.getUserStatus(), result.getUserStatus());
        }

        @Test
        @DisplayName("회원가입 실패 케이스")
        void singUpFailure() {
            UserDTO userDTO = UserDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .userName("testName")
                    .userNickname("testNickname")
                    .userEmail("testEmail@test.com")
                    .userSchoolSchoolName("Test University")
                    .userRegisteredYear(2019)
                    .userProfile("")
                    .userSchoolVerified(true)
                    .userPoint(0L)
                    .userStatus(UserStatus.ACTIVE)
                    .build();

            UserDTO result = userController.singup(userDTO).getBody();

            assertNull(result);
        }
    }

    @Nested
    @DisplayName("회원 정보 수정 테스트")
    class updateUser {
        @Test
        @DisplayName("회원 정보 수정 성공 케이스")
        @Transactional
        void updateUserS() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .build();

            String token = userController.login(userLoginDTO).getBody();
            UserDTO userInfo = userController.info(token).getBody();

            userInfo.setUserEmail("changedEmail@mail.com");
            userInfo.setUserNickname("changedNickname");

            UserDTO result = userController.update(token, userInfo).getBody();

            assertEquals(userInfo.getUserEmail(), result.getUserEmail());
            assertEquals(userInfo.getUserNickname(), result.getUserNickname());
        }
    }

    @Nested
    @DisplayName("회원 탈퇴 테스트")
    class UserDeleteTest {

        @Test
        @DisplayName("회원 탈퇴 성공 케이스(글 없을때)")
        @Transactional
        void userDeleteS() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .build();
            String token = userController.login(userLoginDTO).getBody();
            String result = userController.delete(token).getBody();

            assertEquals("유저 삭제 완료", result);
        }

        @Test
        @DisplayName("회원 탈퇴 성공 케이스(글 있을 때)")
        @Transactional
        void userDeleteWithBoard() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .build();
            String token = userController.login(userLoginDTO).getBody();

            BoardRequestDTO boardRequestDTO = BoardRequestDTO.builder()
                    .boardTitle("testTitle")
                    .content("testContent")
                    .boardTypePK(boardTypeDTO.getBoardTypePK())
                    .anonymity(true)
                    .build();

            BoardResponseDTO board = boardController.createBoard(boardRequestDTO, token).getBody();

            assert (board != null);

            System.out.println(board.toString());

            String result = userController.delete(token).getBody();

            System.out.println(result);
            assertEquals("유저 삭제 완료", result);
        }
    }

    @Nested
    class info {
        @Test
        @DisplayName("회원 정보 조회 성공 케이스")
        void infoS() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .build();
            String token = userController.login(userLoginDTO).getBody();
            UserDTO result = userController.info(token).getBody();

            assertEquals(userLoginDTO.getUserId(), result.getUserId());
            assert (encoder.matches(userLoginDTO.getUserPwd(), result.getUserPwd()));
        }

        @Test
        @DisplayName("회원 정보 조회 실패 케이스")
        void infoF() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId12")
                    .userPwd("testPwd12")
                    .build();
            String token = userController.login(userLoginDTO).getBody();

            assertNull(token);
        }

    }

    @Nested
    @DisplayName("비밀번호 변경 테스트")
    class userPasswordModifyTest {
        @Test
        @DisplayName("비밀번호 변경 성공 케이스")
        @Transactional
        void userPasswordModifyS() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .build();
            String token = userController.login(userLoginDTO).getBody();

            UserChangePasswordDTO userChangePasswordDTO = UserChangePasswordDTO.builder()
                    .userPwd("testPwd")
                    .userNewPwd("newTestPwd")
                    .userNewPwd2("newTestPwd")
                    .build();

            String result = userController.UserPasswordModify(userChangePasswordDTO, token).getBody();

            userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("newTestPwd")
                    .build();
            token = userController.login(userLoginDTO).getBody();
            UserDTO userDTO = userController.info(token).getBody();

            assertTrue(encoder.matches("newTestPwd", userDTO.getUserPwd()));

            assertEquals("비밀번호 변경 완료", result);
        }

        @Test
        @DisplayName("비밀번호 변경 실패 케이스 (현재 비밀번호가 불일치)")
        @Transactional
        void userPasswordModifyF() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .build();
            String token = userController.login(userLoginDTO).getBody();

            UserChangePasswordDTO userChangePasswordDTO = UserChangePasswordDTO.builder()
                    .userPwd("testPwd11")
                    .userNewPwd("newTestPwd")
                    .userNewPwd2("newTestPwd")
                    .build();

            String result = userController.UserPasswordModify(userChangePasswordDTO, token).getBody();

            assertEquals("현재 비밀번호가 일치하지 않습니다.", result);
        }

        @Test
        @DisplayName("비밀번호 변경 실패 케이스 (새 비밀번호가 불일치)")
        @Transactional
        void userPasswordModifyF2() {
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .userId("testId")
                    .userPwd("testPwd")
                    .build();
            String token = userController.login(userLoginDTO).getBody();

            UserChangePasswordDTO userChangePasswordDTO = UserChangePasswordDTO.builder()
                    .userPwd("testPwd")
                    .userNewPwd("newTestPwd1")
                    .userNewPwd2("newTestPwd")
                    .build();

            String result = userController.UserPasswordModify(userChangePasswordDTO, token).getBody();

            assertEquals("비밀번호가 일치하지 않습니다.", result);
        }
    }
}