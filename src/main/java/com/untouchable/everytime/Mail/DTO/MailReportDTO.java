package com.untouchable.everytime.Mail.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailReportDTO {

    Long MailReport_PK;

    Long report_PK;

}
