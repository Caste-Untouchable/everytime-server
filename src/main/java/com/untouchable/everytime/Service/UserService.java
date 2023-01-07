package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Repository.SchoolRepository;
import com.untouchable.everytime.Repository.UserRepository;
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
        UserEntity userEntity = userRepository.findById(ID).get();

        if (encoder.matches(PWD, userEntity.getPWD())) {
            return ResponseEntity.ok(jwtConfig.createToken(userEntity));
            //return modelMapper.map(userEntity, UserDTO.class);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<UserDTO> register(UserDTO userDTO) {

        if (userRepository.existsByUserID(userDTO.getUserID())) {
            return ResponseEntity.badRequest().build();
        }

        String password = encoder.encode(userDTO.getPWD());
        userDTO.setPWD(password);
        userDTO.setPoint(0L);
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        userEntity.setSchool(schoolRepository.findById(userDTO.getSchoolName()).get());

        userEntity = userRepository.save(userEntity);

        return ResponseEntity.ok(modelMapper.map(userEntity, UserDTO.class));
    }

    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO, String token) {
        Map<String, Object> result = jwtConfig.verifyJWT(token);
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return ResponseEntity.ok(modelMapper.map(userEntity, UserDTO.class));
    }

    public ResponseEntity deleteUser(String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        userRepository.deleteById(String.valueOf(jwt.get("ID")));
        return ResponseEntity.ok("유저 삭제 완료");
    }

    public ResponseEntity<UserDTO> getUserById(String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        Optional<UserEntity> user = userRepository.findById(String.valueOf(jwt.get("ID")));

        if (user.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(user.get(), UserDTO.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
