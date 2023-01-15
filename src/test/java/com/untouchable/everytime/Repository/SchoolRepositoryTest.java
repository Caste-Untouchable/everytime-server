package com.untouchable.everytime.Repository;

import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SchoolRepositoryTest {

    @Autowired
    SchoolRepository schoolRepository;


    @Test
    @DisplayName("학교 생성")
    void createSchool(){
        // given
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

        // when
        School savedSchool = schoolRepository.save(school);

        // then
        assertEquals(school.getSchoolName(), savedSchool.getSchoolName());
        assertEquals(school.getSchoolLocation(), savedSchool.getSchoolLocation());
        assertEquals(school.getSchoolTell(), savedSchool.getSchoolTell());
        assertEquals(school.getSchoolFax(), savedSchool.getSchoolFax());
        assertEquals(school.getSchoolUrlHome(), savedSchool.getSchoolUrlHome());
        assertEquals(school.getSchoolUrlStudyRoom(), savedSchool.getSchoolUrlStudyRoom());
        assertEquals(school.getSchoolUrlShuttle(), savedSchool.getSchoolUrlShuttle());
        assertEquals(school.getSchoolUrlNotice(), savedSchool.getSchoolUrlNotice());
        assertEquals(school.getSchoolUrlCalendar(), savedSchool.getSchoolUrlCalendar());
        assertEquals(school.getSchoolUrlLibrary(), savedSchool.getSchoolUrlLibrary());
    }

    @Test
    @DisplayName("학교 조회")
    void find(){
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

        // when
        School savedSchool = schoolRepository.save(school);
        School findSchool = schoolRepository.findById("한국대학교").get();

        // then
        assertEquals(savedSchool.getSchoolName(), findSchool.getSchoolName());
        assertEquals(savedSchool.getSchoolLocation(), findSchool.getSchoolLocation());
        assertEquals(savedSchool.getSchoolTell(), findSchool.getSchoolTell());
        assertEquals(savedSchool.getSchoolFax(), findSchool.getSchoolFax());
        assertEquals(savedSchool.getSchoolUrlHome(), findSchool.getSchoolUrlHome());
        assertEquals(savedSchool.getSchoolUrlStudyRoom(), findSchool.getSchoolUrlStudyRoom());
        assertEquals(savedSchool.getSchoolUrlShuttle(), findSchool.getSchoolUrlShuttle());
        assertEquals(savedSchool.getSchoolUrlNotice(), findSchool.getSchoolUrlNotice());
        assertEquals(savedSchool.getSchoolUrlCalendar(), findSchool.getSchoolUrlCalendar());
        assertEquals(savedSchool.getSchoolUrlLibrary(), findSchool.getSchoolUrlLibrary());
    }

    @Test
    @DisplayName("학교 조회 False")
    void findFalse(){
        assertFalse(schoolRepository.findById("한국대학교").isPresent());
    }


}
