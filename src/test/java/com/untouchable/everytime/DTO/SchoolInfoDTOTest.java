package com.untouchable.everytime.DTO;

import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.DTO.SchoolInfoDTO;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SchoolInfoDTOTest {

    ModelMapper modelMapper;
    SchoolInfoDTO schoolInfoDTO;

    @BeforeAll
    void setUp() {
        modelMapper = new ModelMapper();
    }

    @BeforeEach
    void clearUserDTO() {
        schoolInfoDTO = SchoolInfoDTO.builder()
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
    }

    @Test
    @DisplayName("toEntity")
    void toEntity(){
        School school = modelMapper.map(schoolInfoDTO, School.class);

        assertEquals(schoolInfoDTO.getSchoolName(), school.getSchoolName());
        assertEquals(schoolInfoDTO.getSchoolLocation(), school.getSchoolLocation());
        assertEquals(schoolInfoDTO.getSchoolTell(), school.getSchoolTell());
        assertEquals(schoolInfoDTO.getSchoolFax(), school.getSchoolFax());
        assertEquals(schoolInfoDTO.getSchoolUrlHome(), school.getSchoolUrlHome());
        assertEquals(schoolInfoDTO.getSchoolUrlStudyRoom(), school.getSchoolUrlStudyRoom());
        assertEquals(schoolInfoDTO.getSchoolUrlShuttle(), school.getSchoolUrlShuttle());
        assertEquals(schoolInfoDTO.getSchoolUrlNotice(), school.getSchoolUrlNotice());
        assertEquals(schoolInfoDTO.getSchoolUrlCalendar(), school.getSchoolUrlCalendar());
        assertEquals(schoolInfoDTO.getSchoolUrlLibrary(), school.getSchoolUrlLibrary());

    }


    @Test
    void getSchoolName() {
        assertEquals("한국대학교", schoolInfoDTO.getSchoolName());
    }

    @Test
    void getSchoolLocation() {
        assertEquals("한국 어딘가", schoolInfoDTO.getSchoolLocation());
    }

    @Test
    void getSchoolTell() {
        assertEquals("02-1234-5678", schoolInfoDTO.getSchoolTell());
    }

    @Test
    void getSchoolFax() {
        assertEquals("02-1234-5678", schoolInfoDTO.getSchoolFax());
    }

    @Test
    void getSchoolUrlHome() {
        assertEquals("www.koreauniv.com", schoolInfoDTO.getSchoolUrlHome());
    }

    @Test
    void getSchoolUrlStudyRoom() {
        assertEquals("www.koreauniv.com/studyroom", schoolInfoDTO.getSchoolUrlStudyRoom());
    }

    @Test
    void getSchoolUrlShuttle() {
        assertEquals("www.koreauniv.com/shuttle", schoolInfoDTO.getSchoolUrlShuttle());
    }

    @Test
    void getSchoolUrlNotice() {
        assertEquals("www.koreauniv.com/notice", schoolInfoDTO.getSchoolUrlNotice());
    }

    @Test
    void getSchoolUrlCalendar() {
        assertEquals("www.koreauniv.com/calendar", schoolInfoDTO.getSchoolUrlCalendar());
    }

    @Test
    void getSchoolUrlLibrary() {
        assertEquals("www.koreauniv.com/library", schoolInfoDTO.getSchoolUrlLibrary());
    }

    @Test
    void setSchoolName() {
        schoolInfoDTO.setSchoolName("한국대학교");
        assertEquals("한국대학교", schoolInfoDTO.getSchoolName());
    }

    @Test
    void setSchoolLocation() {
        schoolInfoDTO.setSchoolLocation("한국 어딘가");
        assertEquals("한국 어딘가", schoolInfoDTO.getSchoolLocation());
    }

    @Test
    void setSchoolTell() {
        schoolInfoDTO.setSchoolTell("02-2345-5678");
        assertEquals("02-2345-5678", schoolInfoDTO.getSchoolTell());
    }

    @Test
    void setSchoolFax() {
        schoolInfoDTO.setSchoolFax("02-2345-5678");
        assertEquals("02-2345-5678", schoolInfoDTO.getSchoolFax());
    }

    @Test
    void setSchoolUrlHome() {
        schoolInfoDTO.setSchoolUrlHome("www.koreauniv-fix.com");
        assertEquals("www.koreauniv-fix.com", schoolInfoDTO.getSchoolUrlHome());
    }

    @Test
    void setSchoolUrlStudyRoom() {
        schoolInfoDTO.setSchoolUrlStudyRoom("www.koreauniv-fix.com/studyroom");
        assertEquals("www.koreauniv-fix.com/studyroom", schoolInfoDTO.getSchoolUrlStudyRoom());
    }

    @Test
    void setSchoolUrlShuttle() {
        schoolInfoDTO.setSchoolUrlShuttle("www.koreauniv-fix.com/shuttle");
        assertEquals("www.koreauniv-fix.com/shuttle", schoolInfoDTO.getSchoolUrlShuttle());
    }

    @Test
    void setSchoolUrlNotice() {
        schoolInfoDTO.setSchoolUrlNotice("www.koreauniv-fix.com/notice");
        assertEquals("www.koreauniv-fix.com/notice", schoolInfoDTO.getSchoolUrlNotice());
    }

    @Test
    void setSchoolUrlCalendar() {
        schoolInfoDTO.setSchoolUrlNotice("www.koreauniv-fix.com/calendar");
        assertEquals("www.koreauniv-fix.com/calendar", schoolInfoDTO.getSchoolUrlNotice());
    }

    @Test
    void setSchoolUrlLibrary() {
        schoolInfoDTO.setSchoolUrlLibrary("www.koreauniv-fix.com/library");
        assertEquals("www.koreauniv-fix.com/library", schoolInfoDTO.getSchoolUrlLibrary());
    }
}
