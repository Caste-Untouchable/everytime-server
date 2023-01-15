package com.untouchable.everytime.User.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.DTO.UserChangePasswordDTO;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    SchoolRepository schoolRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;
    PasswordEncoder encoder;

    @Autowired
    public UserService(SchoolRepository schoolRepository, UserRepository userRepository, PasswordEncoder encoder, JwtConfig jwtConfig, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtConfig = jwtConfig;
        this.modelMapper = modelMapper;
        this.schoolRepository = schoolRepository;
    }

    public ResponseEntity<String> login(String ID, String PWD) {
        User user = userRepository.findById(ID).get();

        if (encoder.matches(PWD, user.getUserPwd())) {
            return ResponseEntity.ok(jwtConfig.createToken(user));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<UserDTO> register(UserDTO userDTO) {

        if (userRepository.existsByUserId(userDTO.getUserId())) {
            return ResponseEntity.badRequest().build();
        }

        String password = encoder.encode(userDTO.getUserPwd());
        userDTO.setUserPwd(password);
        userDTO.setUserPoint(0L);
        User user = modelMapper.map(userDTO, User.class);

        user.setUserSchool(schoolRepository.findById(userDTO.getUserSchoolSchoolName()).get());

        user = userRepository.save(user);

        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }

    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO, String token) {
        Map<String, Object> result = jwtConfig.verifyJWT(token);
        User newUserInfo = modelMapper.map(userDTO, User.class);
        User userInfo = userRepository.findById((String) result.get("userId")).get();

        //이메일 변경
        if (!newUserInfo.getUserEmail().isEmpty() || !userInfo.getUserEmail().equals(newUserInfo.getUserEmail())) {
            userInfo.setUserEmail(newUserInfo.getUserEmail());
        }

        // 닉네임 변경
        if (!newUserInfo.getUserNickname().isEmpty() || !userInfo.getUserNickname().equals(newUserInfo.getUserNickname())) {
            userInfo.setUserNickname(newUserInfo.getUserNickname());
        }

        userInfo = userRepository.save(userInfo);
        return ResponseEntity.ok(modelMapper.map(userInfo, UserDTO.class));
    }

    public ResponseEntity deleteUser(String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        userRepository.deleteById(String.valueOf(jwt.get("userId")));
        return ResponseEntity.ok("유저 삭제 완료");
    }

    public ResponseEntity<UserDTO> getUserByToken(String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        Optional<User> user = userRepository.findById(String.valueOf(jwt.get("userId")));

        if (user.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(user.get(), UserDTO.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Boolean> userDuplicationCheck(String userId) {
        return ResponseEntity.ok(!userRepository.existsByUserId(userId));
    }

    public ResponseEntity<String> modifyUserPassword(UserChangePasswordDTO userChangePasswordDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        User user = userRepository.findById(String.valueOf(jwt.get("userId"))).get();

        // 현재 비밀번호가 일치하지 않을 경우
        if (!encoder.matches(userChangePasswordDTO.getUserPwd(), user.getUserPwd())) {
            return ResponseEntity.badRequest().build();
        }
        // 비밀번호 2번째랑 일치 하지 않을 경우
        if (!userChangePasswordDTO.getUserNewPwd().equals(userChangePasswordDTO.getUserNewPwd2())) {
            return ResponseEntity.badRequest().build();
        }
        user.setUserPwd(encoder.encode(userChangePasswordDTO.getUserNewPwd()));
        userRepository.save(user);
        return ResponseEntity.ok("비밀번호 변경 완료");
    }
}
