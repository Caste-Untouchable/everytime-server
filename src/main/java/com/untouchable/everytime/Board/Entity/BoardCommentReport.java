package com.untouchable.everytime.Board.Entity;

import com.untouchable.everytime.Board.Enum.ReportType;
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
public class BoardCommentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCommentReportPK;

    @Enumerated(EnumType.STRING)
    ReportType reportType;

    @ManyToOne
    User reportUser;

    @ManyToOne
    BoardComment reportBoardComment;
}
