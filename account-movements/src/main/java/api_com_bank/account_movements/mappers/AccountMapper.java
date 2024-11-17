package api_com_bank.account_movements.mappers;

import api_com_bank.account_movements.dtos.reports.AccountsDataReportDTO;
import api_com_bank.account_movements.dtos.reports.MovementReportDTO;
import api_com_bank.account_movements.dtos.request.AccountRequestDTO;
import api_com_bank.account_movements.dtos.response.AccountResponseDTO;
import api_com_bank.account_movements.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountEntity toEntity(AccountRequestDTO dto);

    AccountResponseDTO toDto(AccountEntity entity);

    default AccountsDataReportDTO toReportDto(AccountEntity entity, List<MovementReportDTO> movements) {
        return entity == null ? null : new AccountsDataReportDTO(
                entity.getAccountNumber(),
                entity.getAccountType(),
                entity.isState(),
                movements
        );
    }

}
