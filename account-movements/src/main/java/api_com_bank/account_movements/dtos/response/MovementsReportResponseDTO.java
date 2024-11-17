package api_com_bank.account_movements.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MovementsReportResponseDTO {

    private String clientName;
    private List<MovementReportDTO> movements;

}
