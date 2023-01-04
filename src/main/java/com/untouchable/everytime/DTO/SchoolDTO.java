package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Entity.FoodEntity;
import com.untouchable.everytime.Entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {
    String SchoolName;
    String location;
    String tell;
    String fax;
    String urlHome;
    String urlStudyRoom;
    String urlShuttle;
    String urlNotice;
    String urlCalendar;
    String urlLibrary;
}
