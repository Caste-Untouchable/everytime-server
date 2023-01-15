package com.untouchable.everytime.Board.Entity;

import com.untouchable.everytime.Enum.BoardTypeClass;
import com.untouchable.everytime.School.Entity.School;
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
    School school;

    String boardType;

    @Enumerated(EnumType.STRING)
    BoardTypeClass boardTypeClass;

    String boardDescription;

}
