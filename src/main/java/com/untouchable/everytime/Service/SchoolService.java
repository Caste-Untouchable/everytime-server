package com.untouchable.everytime.Service;

import com.untouchable.everytime.DTO.SchoolDTO;
import com.untouchable.everytime.Entity.SchoolEntity;
import com.untouchable.everytime.Repository.SchoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    SchoolRepository schoolRepository;
    ModelMapper modelMapper;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository, ModelMapper modelMapper) {
        this.schoolRepository = schoolRepository;
        this.modelMapper = modelMapper;
    }

    public SchoolDTO createSchool(SchoolDTO schoolDTO) {
        SchoolEntity schoolEntity = modelMapper.map(schoolDTO, SchoolEntity.class);
        schoolRepository.save(schoolEntity);
        return modelMapper.map(schoolEntity, SchoolDTO.class);
    }

    public ResponseEntity<SchoolDTO> getSchool(String id) {
        Optional<SchoolEntity> schoolEntity = schoolRepository.findById(id);
        if (schoolEntity.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(schoolEntity, SchoolDTO.class));
        }
        return ResponseEntity.notFound().build();

    }

    public SchoolDTO updateSchool(SchoolDTO schoolDTO) {
        SchoolEntity schoolEntity = modelMapper.map(schoolDTO, SchoolEntity.class);
        schoolRepository.save(schoolEntity);
        return modelMapper.map(schoolEntity, SchoolDTO.class);
    }

    public void deleteSchool(String id) {
        schoolRepository.deleteById(id);
    }

    public ArrayList<SchoolDTO> findAllSchool() {
        List<SchoolEntity> schoolEntities = schoolRepository.findAll();
        ArrayList<SchoolDTO> schoolDTOS = new ArrayList<>();
        for (SchoolEntity schoolEntity : schoolEntities) {
            schoolDTOS.add(modelMapper.map(schoolEntity, SchoolDTO.class));
        }
        return schoolDTOS;

    }
}
