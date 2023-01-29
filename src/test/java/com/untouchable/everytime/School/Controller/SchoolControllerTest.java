package com.untouchable.everytime.School.Controller;

import com.untouchable.everytime.School.DTO.SchoolInfoDTO;
import com.untouchable.everytime.School.DTO.SchoolListDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SchoolControllerTest {

    @Autowired
    private SchoolController schoolController;

    @BeforeAll
    void setUp() {
        SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
                .schoolName("테스트 대학교")
                .schoolLocation("대한민국 어딘가")
                .schoolTell("010-1111-2222")
                .schoolFax("010-2222-3333")
                .schoolUrlHome("https://www.test.com")
                .schoolUrlStudyRoom("https://www.test.com")
                .schoolUrlShuttle("https://www.test.com")
                .schoolUrlNotice("https://www.test.com")
                .schoolUrlCalendar("https://www.test.com")
                .schoolUrlLibrary("https://www.test.com")
                .build();
        schoolController.createSchool(schoolInfoDTO);
    }

    @Nested
    @DisplayName("학교 생성 테스트")
    class createSchool {
        @Test
        @DisplayName("학교 생성 성공")
        @Transactional
        void createSchoolSuccess() {
            SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
                    .schoolName("테스트 대학교12")
                    .schoolLocation("대한민국 어딘가123")
                    .schoolTell("010-1111-2222")
                    .schoolFax("010-2222-3333")
                    .schoolUrlHome("https://www.test.com")
                    .schoolUrlStudyRoom("https://www.test.com")
                    .schoolUrlShuttle("https://www.test.com")
                    .schoolUrlNotice("https://www.test.com")
                    .schoolUrlCalendar("https://www.test.com")
                    .schoolUrlLibrary("https://www.test.com")
                    .build();
            SchoolInfoDTO result = schoolController.createSchool(schoolInfoDTO).getBody();
            assertEquals(result, schoolInfoDTO);
        }

        @Test
        @DisplayName("학교 생성 실패")
        @Transactional
        void createSchoolFailure() {
            SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
                    .schoolName("테스트 대학교")
                    .schoolLocation("대한민국 어딘가")
                    .schoolTell("010-1111-2222")
                    .schoolFax("010-2222-3333")
                    .schoolUrlHome("https://www.test.com")
                    .schoolUrlStudyRoom("https://www.test.com")
                    .schoolUrlShuttle("https://www.test.com")
                    .schoolUrlNotice("https://www.test.com")
                    .schoolUrlCalendar("https://www.test.com")
                    .schoolUrlLibrary("https://www.test.com")
                    .build();

            SchoolInfoDTO result = schoolController.createSchool(schoolInfoDTO).getBody();
            assertNull(result);
        }

    }

    @Nested
    @DisplayName("학교 조회 테스트")
    class getSchool {
        @Test
        @DisplayName("학교 조회 성공")
        @Transactional
        void getSchoolSuccess() {
            SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
                    .schoolName("테스트 대학교")
                    .schoolLocation("대한민국 어딘가")
                    .schoolTell("010-1111-2222")
                    .schoolFax("010-2222-3333")
                    .schoolUrlHome("https://www.test.com")
                    .schoolUrlStudyRoom("https://www.test.com")
                    .schoolUrlShuttle("https://www.test.com")
                    .schoolUrlNotice("https://www.test.com")
                    .schoolUrlCalendar("https://www.test.com")
                    .schoolUrlLibrary("https://www.test.com")
                    .build();
            SchoolInfoDTO result = schoolController.getSchool(schoolInfoDTO.getSchoolName()).getBody();
            assertEquals(result, schoolInfoDTO);
        }

        @Test
        @DisplayName("학교 조회 실패")
        @Transactional
        void getSchoolFailure() {
            SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
                    .schoolName("테스트 대학교12")
                    .schoolLocation("대한민국 어딘가123")
                    .schoolTell("010-1111-2222")
                    .schoolFax("010-2222-3333")
                    .schoolUrlHome("https://www.test.com")
                    .schoolUrlStudyRoom("https://www.test.com")
                    .schoolUrlShuttle("https://www.test.com")
                    .schoolUrlNotice("https://www.test.com")
                    .schoolUrlCalendar("https://www.test.com")
                    .schoolUrlLibrary("https://www.test.com")
                    .build();
            SchoolInfoDTO result = schoolController.getSchool(schoolInfoDTO.getSchoolName()).getBody();
            assertNull(result);
        }
    }

    @Nested
    @DisplayName("학교 수정 테스트")
    class updateSchool {
        @Test
        @DisplayName("학교 수정 성공")
        @Transactional
        void updateSchoolSuccess() {
            SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
                    .schoolName("테스트 대학교")
                    .schoolLocation("대한민국 어딘가")
                    .schoolTell("010-1111-2222")
                    .schoolFax("010-2222-3333")
                    .schoolUrlHome("https://www.test.com")
                    .schoolUrlStudyRoom("https://www.test.com")
                    .schoolUrlShuttle("https://www.test.com")
                    .schoolUrlNotice("https://www.test.com")
                    .schoolUrlCalendar("https://www.test.com")
                    .schoolUrlLibrary("https://www.test.com")
                    .build();
            SchoolInfoDTO result = schoolController.updateSchool(schoolInfoDTO).getBody();
            assertEquals(result, schoolInfoDTO);
        }

        @Test
        @DisplayName("학교 수정 실패")
        @Transactional
        void updateSchoolFailure() {
            SchoolInfoDTO schoolInfoDTO = schoolController.getSchool("테스트 대학교").getBody();
            schoolInfoDTO.setSchoolName("테스트 대학교12");
            schoolInfoDTO.setSchoolLocation("대한민국 어딘가123");
            schoolInfoDTO.setSchoolTell("010-1111-1234");
            schoolInfoDTO.setSchoolFax("010-2222-1234");
            schoolInfoDTO.setSchoolUrlCalendar("https://www.test11.com");
            schoolInfoDTO.setSchoolUrlHome("https://www.test22.com");
            schoolInfoDTO.setSchoolUrlLibrary("https://www.test33.com");
            schoolInfoDTO.setSchoolUrlNotice("https://www.test44.com");
            schoolInfoDTO.setSchoolUrlShuttle("https://www.test55.com");
            schoolInfoDTO.setSchoolUrlStudyRoom("https://www.test66.com");


            SchoolInfoDTO result = schoolController.updateSchool(schoolInfoDTO).getBody();
            assertNull(result);
        }

    }

    @Nested
    @DisplayName("학교 삭제 테스트")
    class deleteSchool {

    }

    @Nested
    @DisplayName("학교 전체 조회 테스트")
    class findAllSchool {
        @Test
        @DisplayName("학교 전체 조회 성공")
        @Transactional
        void findAllSchoolSuccess() {
            ArrayList<SchoolListDTO> SchoolListDTOS = schoolController.findAllSchool().getBody();

            assertTrue(SchoolListDTOS.contains(SchoolListDTO.builder().schoolName("테스트 대학교").build()));
        }
    }
}