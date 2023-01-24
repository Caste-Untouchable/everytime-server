package com.untouchable.everytime.Board.DTO;

import com.untouchable.everytime.Board.Enum.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCommentReportDTO {
    Long boardCommentReport_PK;
    ReportType reportType;
    String User_ID;
    Long boardComment_PK;
}
