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

    String userUserId;
    String originFilename;
    String storeFilename;
    AttachmentType attachmentType;

}
