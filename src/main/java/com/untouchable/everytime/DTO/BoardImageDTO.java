package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Enum.AttachmentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardImageDTO {

    Long boardImage_PK;
    String originFilename;
    String storeFilename;

    AttachmentType attachmentType;

    Long board_UK;

}
