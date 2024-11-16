package api_com_bank.account_movements.mappers;

import api_com_bank.account_movements.dtos.request.CreateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.response.MovementsResponseDTO;
import api_com_bank.account_movements.entities.MovementsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovementsMapper {

    MovementsMapper INSTANCE = Mappers.getMapper(MovementsMapper.class);

    MovementsEntity toEntityCreate(CreateMovementsRequestDTO dto);

    MovementsEntity toEntityUpdate(UpdateMovementsRequestDTO dto);

    MovementsResponseDTO toDto(MovementsEntity entity);

}
