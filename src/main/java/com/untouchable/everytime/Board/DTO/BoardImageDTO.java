package com.untouchable.everytime.Board.DTO;

import com.untouchable.everytime.Enum.AttachmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardImageDTO {

    Long boardImagePK;
    String originFilename;
    String storeFilename;

    AttachmentType attachmentType;

    Long boardPK;

}
