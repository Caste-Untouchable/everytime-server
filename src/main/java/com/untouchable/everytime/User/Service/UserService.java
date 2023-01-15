package com.untouchable.everytime.User.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.School.Repository.SchoolRepository;
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
            //return modelMapper.map(userEntity, UserDTO.class);
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
        User user = modelMapper.map(userDTO, User.class);
        user.setUserPwd(encoder.encode(userDTO.getUserId()));

        user = userRepository.save(user);
        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }

    public ResponseEntity deleteUser(String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        userRepository.deleteById(String.valueOf(jwt.get("ID")));
        return ResponseEntity.ok("유저 삭제 완료");
    }

    public ResponseEntity<UserDTO> getUserById(String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        Optional<User> user = userRepository.findById(String.valueOf(jwt.get("ID")));

        if (user.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(user.get(), UserDTO.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Boolean> userDuplicationCheck(String userId){
        return ResponseEntity.ok(!userRepository.existsByUserId(userId));
    }


}
