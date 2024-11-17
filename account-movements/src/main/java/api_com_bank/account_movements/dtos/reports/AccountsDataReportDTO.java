package api_com_bank.account_movements.dtos.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDataReportDTO {
    private String number;
    private String type;
    private boolean state;
    private List<MovementReportDTO> movements;
}
