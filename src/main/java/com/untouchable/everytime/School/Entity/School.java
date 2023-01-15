package com.untouchable.everytime.School.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    String schoolName;
    String schoolLocation;
    String schoolTell;
    String schoolFax;
    String schoolUrlHome;
    String schoolUrlStudyRoom;
    String schoolUrlShuttle;
    String schoolUrlNotice;
    String schoolUrlCalendar;
    String schoolUrlLibrary;
}
