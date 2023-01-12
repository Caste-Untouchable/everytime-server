package com.untouchable.everytime.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {
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
