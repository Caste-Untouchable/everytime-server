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
public class SchoolEntity {
    @Id
    long SPK;
    @OneToMany
    ArrayList<FoodEntity> food;
    @OneToMany
    ArrayList<UserEntity> user;
    String name;
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
