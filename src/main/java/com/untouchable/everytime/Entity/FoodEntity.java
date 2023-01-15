package com.untouchable.everytime.Entity;

import com.untouchable.everytime.School.Entity.School;
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
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long foodPK;
    @ManyToOne
    School school;
    String place;
    String menu;
    Date date;
}
