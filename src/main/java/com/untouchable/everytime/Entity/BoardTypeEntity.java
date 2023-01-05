package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.BoardTypeClass;
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
public class BoardTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardTypePK;

    @ManyToOne
    SchoolEntity school;

    String boardType;

    @Enumerated(EnumType.STRING)
    BoardTypeClass boardTypeClass;

    String boardDescription;

}
