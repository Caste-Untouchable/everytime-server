package com.untouchable.everytime.Schedule.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Schedule.DTO.ScheduleDTO;
import com.untouchable.everytime.Schedule.Entity.ScheduleEntity;
import com.untouchable.everytime.Schedule.Repository.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService {

    ScheduleRepository scheduleRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;

    @Autowired
    public ScheduleService(JwtConfig jwtConfig,ScheduleRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
        this.jwtConfig = jwtConfig;
    }


    public ResponseEntity<ScheduleDTO> createSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = modelMapper.map(scheduleDTO, ScheduleEntity.class);
        scheduleEntity = scheduleRepository.save(scheduleEntity);

        return ResponseEntity.ok(modelMapper.map(scheduleEntity, ScheduleDTO.class));

    }

    public ResponseEntity<ScheduleDTO> updateSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = modelMapper.map(scheduleDTO, ScheduleEntity.class);
        scheduleEntity = scheduleRepository.save(scheduleEntity);

        return ResponseEntity.ok(modelMapper.map(scheduleEntity, ScheduleDTO.class));

    }
    public ResponseEntity<ScheduleDTO> deleteSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = modelMapper.map(scheduleDTO, ScheduleEntity.class);
        scheduleRepository.delete(scheduleEntity);

        return ResponseEntity.ok(modelMapper.map(scheduleEntity, ScheduleDTO.class));

    }

    public ResponseEntity<ArrayList<ScheduleDTO>> findByLecture_LecturePK(String token,Long id) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findByLecture_LecturePK(id);

        ArrayList<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (ScheduleEntity scheduleEntity : scheduleEntities) {
            scheduleDTOList.add(modelMapper.map(scheduleEntity, ScheduleDTO.class));
        }

        return ResponseEntity.ok(scheduleDTOList);
    }

}
