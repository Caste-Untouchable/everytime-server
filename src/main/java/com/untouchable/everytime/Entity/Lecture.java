package com.untouchable.everytime.Entity;

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

public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long LPK;

    @Column
    int lectureNum;
    int separation;
    String lecutureName;

}
