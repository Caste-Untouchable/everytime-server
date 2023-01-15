package com.untouchable.everytime.Entity;

import com.untouchable.everytime.School.DTO.SchoolInfoDTO;
import com.untouchable.everytime.School.Entity.School;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SchoolTest {
    ModelMapper modelMapper;

    @BeforeAll
    void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("DTO로 변환")
    void toDTO(){
        School school = School.builder()
                .schoolName("한국대학교")
                .schoolLocation("한국 어딘가")
                .schoolTell("02-1234-5678")
                .schoolFax("02-1234-5678")
                .schoolUrlHome("www.koreauniv.com")
                .schoolUrlStudyRoom("www.koreauniv.com/studyroom")
                .schoolUrlShuttle("www.koreauniv.com/shuttle")
                .schoolUrlNotice("www.koreauniv.com/notice")
                .schoolUrlCalendar("www.koreauniv.com/calendar")
                .schoolUrlLibrary("www.koreauniv.com/library")
                .build();

        SchoolInfoDTO schoolInfoDTO = modelMapper.map(school, SchoolInfoDTO.class);

        assertEquals(school.getSchoolName(), schoolInfoDTO.getSchoolName());
        assertEquals(school.getSchoolLocation(), schoolInfoDTO.getSchoolLocation());
        assertEquals(school.getSchoolTell(), schoolInfoDTO.getSchoolTell());
        assertEquals(school.getSchoolFax(), schoolInfoDTO.getSchoolFax());
        assertEquals(school.getSchoolUrlHome(), schoolInfoDTO.getSchoolUrlHome());
        assertEquals(school.getSchoolUrlStudyRoom(), schoolInfoDTO.getSchoolUrlStudyRoom());
        assertEquals(school.getSchoolUrlShuttle(), schoolInfoDTO.getSchoolUrlShuttle());
        assertEquals(school.getSchoolUrlNotice(), schoolInfoDTO.getSchoolUrlNotice());
        assertEquals(school.getSchoolUrlCalendar(), schoolInfoDTO.getSchoolUrlCalendar());
        assertEquals(school.getSchoolUrlLibrary(), schoolInfoDTO.getSchoolUrlLibrary());
    }

    @Test
    @DisplayName("getSchoolName")
    void getSchoolName() {
        School school = School.builder()
                .schoolName("한국대학교").build();

        assertEquals("한국대학교", school.getSchoolName());
    }

}
