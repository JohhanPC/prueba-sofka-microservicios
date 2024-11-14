package api_com_bank.account_movements.controllers.contracts;

import api_com_bank.account_movements.dtos.request.CreateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.response.MoviemientosResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/movimientos")
public interface IMovimientosController{

    @PostMapping(path = "/create")
    ResponseEntity<ResponseDTO> createCuenta(@RequestBody CreateMovimientosRequestDTO createMovimientosRequestDTO);

    @PutMapping(path = "/update")
    ResponseEntity<ResponseDTO> updateCuenta(@RequestBody UpdateMovimientosRequestDTO updateMovimientosRequestDTO);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<ResponseDTO> deleteCuenta(@PathVariable Long id);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<MoviemientosResponseDTO> getMovimientos(@PathVariable Long id);

    @GetMapping(path = "/reportes")
    ResponseEntity<ResponseDTO> getAccountReport(
            @RequestParam String clientId,
            @RequestParam String startDate,
            @RequestParam String endDate
    );

}
