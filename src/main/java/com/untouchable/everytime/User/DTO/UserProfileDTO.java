package com.untouchable.everytime.User.DTO;

import com.untouchable.everytime.Enum.AttachmentType;
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
