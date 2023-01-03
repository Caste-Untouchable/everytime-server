package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Enum.AttachmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

    private Long PK;
    private String originFilename;
    private String storeFilename;

    private AttachmentType attachmentType;


    UserDTO user;

}
