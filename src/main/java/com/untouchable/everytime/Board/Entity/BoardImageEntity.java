package com.untouchable.everytime.Board.Entity;

import com.untouchable.everytime.Enum.AttachmentType;
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
public class BoardImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardImagePK;
    private String originFilename;
    private String storeFilename;
    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;

    @ManyToOne
    BoardEntity board;
}
