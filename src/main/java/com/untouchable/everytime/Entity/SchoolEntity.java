package com.untouchable.everytime.Entity;

import jakarta.persistence.*;
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
    String schoolName;
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
