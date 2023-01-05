package com.untouchable.everytime.Entity;

import com.untouchable.everytime.Enum.ReportType;
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
public class BoardCommentReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCommentReportPK;

    @Enumerated(EnumType.STRING)
    ReportType reportType;

    @ManyToOne
    UserEntity reportUser;

    @ManyToOne
    BoardCommentEntity reportBoardComment;
}
