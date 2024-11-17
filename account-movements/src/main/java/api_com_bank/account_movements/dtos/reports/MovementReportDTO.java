package api_com_bank.account_movements.dtos.reports;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MovementReportDTO {
    @JsonFormat(pattern = "dd/MM/yy, hh:mm a")
    private Date date;
    private Double initialBalance;
    private Double value;
    private Double balance;
}
