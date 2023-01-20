package com.untouchable.everytime.User.Entity;

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
public class UserProfile {
    @Id
    @OneToOne
    User user;

    private String originFilename;
    private String storeFilename;
    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;


}
