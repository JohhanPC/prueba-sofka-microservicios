package api_com_bank.account_movements.services.contracts;

import api_com_bank.account_movements.dtos.messages.AccountCreatedMessage;
import api_com_bank.account_movements.dtos.messages.CustomerCreatedMessage;

public interface IAccountInternalService {

    AccountCreatedMessage processCustomerMessage(CustomerCreatedMessage customerMessage);

}
