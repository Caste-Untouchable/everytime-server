package com.untouchable.everytime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class UserSchedule {
    @Id
    Long userSchedule_PK;

    String scheduleName;

    @OneToMany
    ArrayList<ScheduleEntity> userScheduleList;

    @OneToMany
    ArrayList<ScheduleCustomEntity> userScheduleCustomList;
}
