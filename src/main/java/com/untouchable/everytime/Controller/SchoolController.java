package com.untouchable.everytime.Controller;

import com.untouchable.everytime.DTO.SchoolDTO;
import com.untouchable.everytime.Service.SchoolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Tag(name = "School CRUD", description = "학교 CRUD 관련 API")
@RestController
@RequestMapping("/school")
public class SchoolController {

    SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/create")
    public SchoolDTO createSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.createSchool(schoolDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<SchoolDTO> getSchool(@RequestParam("id") Long id) {
        Optional<SchoolDTO> school = schoolService.getSchool(id);
        if (school.isPresent()) {
            return ResponseEntity.ok(school.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/update")
    public SchoolDTO updateSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.updateSchool(schoolDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteSchool(@RequestParam("id") Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/findAll")
    public ArrayList<SchoolDTO> findAllSchool() {
        return schoolService.findAllSchool();
    }


}
