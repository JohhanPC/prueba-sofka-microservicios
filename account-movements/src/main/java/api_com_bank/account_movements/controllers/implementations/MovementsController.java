package api_com_bank.account_movements.controllers.implementations;

import api_com_bank.account_movements.controllers.contracts.IMovementsController;
import api_com_bank.account_movements.dtos.request.CreateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.response.MovementsResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.services.contracts.IHandlerMessage;
import api_com_bank.account_movements.services.contracts.IMovementsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovementsController implements IMovementsController {

    private final IMovementsServices movementsServices;
    private final IHandlerMessage handlerMessage;

    @Override
    public ResponseEntity<ResponseDTO> create(CreateMovementsRequestDTO createMovementsRequestDTO) {
        ResponseDTO response = movementsServices.create(createMovementsRequestDTO);
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
    public ResponseEntity<MovementsResponseDTO> getMovements(Long id) {
        MovementsResponseDTO movements = movementsServices.getMovements(id);
        if (movements != null) {
            return new ResponseEntity<>(movements, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //TODO implementation
    @Override
    public ResponseEntity<ResponseDTO> getAccountReport(String clientId, String startDate, String endDate) {
        return null;
    }
}
