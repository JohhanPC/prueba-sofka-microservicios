package api_com_bank.account_movements.services.contracts;

import api_com_bank.account_movements.dtos.request.CreateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.response.MovementsResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import org.springframework.web.bind.annotation.*;

public interface IMovementsServices {

    ResponseDTO create(CreateMovementsRequestDTO createMovementsRequestDTO);

    ResponseDTO update(UpdateMovementsRequestDTO updateMovementsRequestDTO);

    ResponseDTO delete(@PathVariable Long id);

    MovementsResponseDTO getMovements(@PathVariable Long id);

}
