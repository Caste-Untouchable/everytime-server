package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.ReportType;
import com.untouchable.everytime.User.Entity.User;
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

public class MailReportEntity {

    @Id
    @OneToOne
    private MailRoomEntity mailRoom;

    @Enumerated(EnumType.STRING)
    ReportType reportType;

    @ManyToOne
    User reportUser;

    @ManyToOne
    User reportedUser;

}
