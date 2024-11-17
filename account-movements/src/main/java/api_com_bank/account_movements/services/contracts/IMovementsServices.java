package api_com_bank.account_movements.services.contracts;

import api_com_bank.account_movements.dtos.reports.AccountMovementsReportResponseDTO;
import api_com_bank.account_movements.dtos.request.CreateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;

import java.util.Date;

public interface IMovementsServices {

    ResponseDTO create(CreateMovementsRequestDTO createMovementsRequestDTO);

    ResponseDTO update(UpdateMovementsRequestDTO updateMovementsRequestDTO);

    ResponseDTO delete(Long id);

    AccountMovementsReportResponseDTO getMovements(String clientId, Date startDate, Date endDate);

}
