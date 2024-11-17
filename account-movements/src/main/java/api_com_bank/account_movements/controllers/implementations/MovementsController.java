package api_com_bank.account_movements.controllers.implementations;

import api_com_bank.account_movements.controllers.contracts.IMovementsController;
import api_com_bank.account_movements.dtos.reports.AccountMovementsReportResponseDTO;
import api_com_bank.account_movements.dtos.request.CreateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.services.contracts.IMovementsServices;
import api_com_bank.account_movements.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class MovementsController implements IMovementsController {

    private final IMovementsServices movementsServices;

    @Override
    public ResponseEntity<ResponseDTO> create(CreateMovementsRequestDTO createMovementsRequestDTO) {
        ResponseDTO response = movementsServices.createValidatedMovement(createMovementsRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> update(UpdateMovementsRequestDTO updateMovementsRequestDTO) {
        ResponseDTO response = movementsServices.update(updateMovementsRequestDTO);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<ResponseDTO> delete(Long id) {
        ResponseDTO response = movementsServices.delete(id);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }


    @Override
    public ResponseEntity<AccountMovementsReportResponseDTO> getAccountReport(String clientId, String startDate, String endDate) {
        Date start = DateUtils.parseDate(startDate);
        Date end = DateUtils.parseDate(endDate);

        if (start == null || end == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AccountMovementsReportResponseDTO report = movementsServices.getMovements(clientId, start, end);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
