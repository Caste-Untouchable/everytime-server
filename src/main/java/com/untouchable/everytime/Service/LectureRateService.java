package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.LectureRateDTO;
import com.untouchable.everytime.Entity.LectureRateEntity;
import com.untouchable.everytime.Repository.LectureRateRepository;
import com.untouchable.everytime.Repository.LectureRepository;
import com.untouchable.everytime.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LectureRateService {

    LectureRateRepository lectureRateRepository;
    LectureRepository lectureRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;

    @Autowired
    public LectureRateService(JwtConfig jwtConfig,ModelMapper strictMapper, LectureRateRepository lectureRateRepository, LectureRepository lectureRepository, UserRepository userRepository) {
        this.lectureRateRepository = lectureRateRepository;
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
        this.modelMapper = strictMapper;
        this.jwtConfig = jwtConfig;
    }


    public ResponseEntity<LectureRateDTO> createLectureRate(LectureRateDTO lectureRateDTO, String token) {
        // 토큰 검증
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        //LectureEntity 만들기
        LectureRateEntity lectureRateEntity = modelMapper.map(lectureRateDTO, LectureRateEntity.class);
        lectureRateEntity.setUser(userRepository.findById(String.valueOf(jwt.get("ID"))).get());
        lectureRateEntity.setLecture(lectureRepository.findById(lectureRateDTO.getLectureLecturePK()).get());

        // lecture 저장
        lectureRateEntity = lectureRateRepository.save(lectureRateEntity);
        return ResponseEntity.ok(modelMapper.map(lectureRateEntity, LectureRateDTO.class));
    }
}
