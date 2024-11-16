package api_com_bank.account_movements.controllers.contracts;

import api_com_bank.account_movements.dtos.request.CreateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.response.MovementsResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/movements")
public interface IMovementsController {

    @PostMapping(path = "/create")
    ResponseEntity<ResponseDTO> create(@RequestBody CreateMovementsRequestDTO createMovementsRequestDTO);

    @PutMapping(path = "/update")
    ResponseEntity<ResponseDTO> update(@RequestBody UpdateMovementsRequestDTO updateMovementsRequestDTO);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<ResponseDTO> delete(@PathVariable Long id);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<MovementsResponseDTO> getMovements(@PathVariable Long id);

    @GetMapping(path = "/report")
    ResponseEntity<ResponseDTO> getAccountReport(
            @RequestParam String clientId,
            @RequestParam String startDate,
            @RequestParam String endDate
    );

}
