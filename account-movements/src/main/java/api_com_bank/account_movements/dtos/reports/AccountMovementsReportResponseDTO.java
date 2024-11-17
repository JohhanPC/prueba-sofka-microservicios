package api_com_bank.account_movements.dtos.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountMovementsReportResponseDTO {
    private ClientReportDTO client;
    private List<AccountsDataReportDTO> accounts;
}
