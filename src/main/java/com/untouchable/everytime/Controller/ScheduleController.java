package com.untouchable.everytime.Controller;

import com.untouchable.everytime.DTO.ScheduleDTO;
import com.untouchable.everytime.Service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/schedule")
@Tag(name = "강의 시간표 API", description = "강의 시간표 대충 CRUD 구현한 API")
public class ScheduleController {

    ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Operation(description = "강의 번호 PK (JWT에서 학교 이름을 가져옴)")
    @GetMapping("/get/{lecturePK}")
    public ResponseEntity<ArrayList<ScheduleDTO>> getScheduleBySchoolName(
            @PathVariable("lecturePK") Long id,
            @RequestHeader("jwt") String token
    ) {
        return scheduleService.findByLecture_LecturePK(token,id);
    }


    @Operation(description = "강의 시간표 생성 API")
    @PostMapping("/create")
    public ResponseEntity<ScheduleDTO> createSchedule(ScheduleDTO scheduleDTO) {
        return scheduleService.createSchedule(scheduleDTO);
    }

    @Operation(description = "강의 시간표 수정 API")
    @PostMapping("/update")
    public ResponseEntity<ScheduleDTO> updateSchedule(ScheduleDTO scheduleDTO) {
        return scheduleService.updateSchedule(scheduleDTO);
    }

    @Operation(description = "강의 시간표 삭제 API")
    @PostMapping("/delete")
    public ResponseEntity<ScheduleDTO> deleteSchedule(ScheduleDTO scheduleDTO) {
        return scheduleService.deleteSchedule(scheduleDTO);
    }

}
