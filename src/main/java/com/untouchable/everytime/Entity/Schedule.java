package com.untouchable.everytime.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Schedule {
    @Id
    private Long pk;

    @Nullable
    @ManyToOne
    UserEntity user;

    @ManyToOne
    SchoolEntity school;


}
