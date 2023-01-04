package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.LectureInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LectureRateEntity {
    @Id
    private Long lectureRate_PK;

    @ManyToOne
    LectureEntity lecture;

    @Column
    String content;
    Date lectureDate;
    int recommendCount;
    Long rate;
    String testInfo;

    @Enumerated(EnumType.STRING)
    LectureInfo lectureInfo;

}
