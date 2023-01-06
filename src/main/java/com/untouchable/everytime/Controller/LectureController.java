package com.untouchable.everytime.Controller;

import com.untouchable.everytime.DTO.LectureDTO;
import com.untouchable.everytime.Service.LectureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//@Tag(name = "Lecture")
@RestController
@RequestMapping("/lecture")
public class LectureController {

    LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/create")
    public LectureDTO createLecture(@RequestBody LectureDTO lectureDTO) {
        return lectureService.createLecture(lectureDTO);
    }

    @PatchMapping("/update")
    public LectureDTO updateLecture(@RequestBody LectureDTO lectureDTO) {
        return lectureService.updateLecture(lectureDTO);
    }

    @DeleteMapping("/delete")
    public void deleteLecture(@RequestBody LectureDTO lectureDTO) {
        lectureService.deleteLecture(lectureDTO);
    }

    @GetMapping("/getList")
    public ArrayList<LectureDTO> getLecture(
            @RequestParam("year") int year,
            @RequestParam("semester") int semester,
            @RequestHeader("jwt") String token) {
        return lectureService.getLectureList(year, semester, token);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<LectureDTO> getLecture(@PathVariable("id") Long id) {
        return lectureService.getLecture(id);
    }


}
