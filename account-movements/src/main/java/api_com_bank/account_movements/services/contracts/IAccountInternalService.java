package api_com_bank.account_movements.services.contracts;

import api_com_bank.account_movements.dtos.message.AccountCreatedMessage;
import api_com_bank.account_movements.dtos.message.CustomerCreatedMessage;

public interface IAccountInternalService {

    AccountCreatedMessage processCustomerMessage(CustomerCreatedMessage customerMessage);

}
