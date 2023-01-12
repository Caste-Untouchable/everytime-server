package com.untouchable.everytime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolEntity {
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
