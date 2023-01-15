package com.untouchable.everytime.Board.DTO;

import com.untouchable.everytime.Board.Enum.ReportType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReportDTO {

    Long boardReportPK;
    ReportType reportType;
    String userID;
    Long boardPK;

}
