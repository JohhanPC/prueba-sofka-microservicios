package api_com_bank.customer.mappers;

import api_com_bank.customer.dtos.request.CustomerRequestDTO;
import api_com_bank.customer.dtos.response.CustomerResponseDTO;
import api_com_bank.customer.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    @Mapping(target = "id", ignore = true)
    CustomerEntity toEntity(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO toDto(CustomerEntity customerEntity);

}
