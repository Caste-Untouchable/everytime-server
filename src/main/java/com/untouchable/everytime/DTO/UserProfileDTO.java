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

    Long userProfile_PK;
    String originFilename;
    String storeFilename;

    AttachmentType attachmentType;

    UserDTO user;

}
