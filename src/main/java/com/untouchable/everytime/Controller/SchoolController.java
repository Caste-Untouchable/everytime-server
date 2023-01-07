package com.untouchable.everytime.Controller;

import com.untouchable.everytime.DTO.SchoolDTO;
import com.untouchable.everytime.Service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Tag(name = "학교", description = "학교 CRUD API")
@RestController
@RequestMapping("/school")
public class SchoolController {

    SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/create")
    @Operation(summary = "학교 생성", description = "학교 생성하는 API")
    public SchoolDTO createSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.createSchool(schoolDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "학교 정보 조회", description = "학교 Info 요청하는 기능")
    public ResponseEntity<SchoolDTO> getSchool(
            @Parameter(name = "대학명", description = "대학교 이름") @RequestParam("id") String id) {
        return schoolService.getSchool(id);

    }

    @PatchMapping("/update")
    @Operation(summary = "학교 정보 수정", description = "학교 정보 수정하는 기능")
    public SchoolDTO updateSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.updateSchool(schoolDTO);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "학교 삭제하는 기능", description = "특정 학교를 삭제하는 기능")
    public ResponseEntity deleteSchool(
            @Parameter(name = "대학명", description = "대학교 이름") @RequestParam("id") String id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/findAll")
    @Operation(summary = "학교명 전부 요청하는 기능", description = "회원가입 시 모든 학교 정보 가져오는 기능")
    public ArrayList<SchoolDTO> findAllSchool() {
        return schoolService.findAllSchool();
    }


}
