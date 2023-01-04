package com.untouchable.everytime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LectureScheduleEntity {
    @Id
    private Long LectureSchedule_PK;

    @ManyToMany
    ArrayList<LectureEntity> lectureName;


}
