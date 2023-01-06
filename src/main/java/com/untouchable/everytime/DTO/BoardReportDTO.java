package com.untouchable.everytime.DTO;

import com.untouchable.everytime.Enum.ReportType;
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
