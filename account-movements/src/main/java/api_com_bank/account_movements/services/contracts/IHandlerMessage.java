package api_com_bank.account_movements.services.contracts;

import api_com_bank.account_movements.dtos.message.AccountCreatedMessage;
import api_com_bank.account_movements.dtos.message.CustomerCreatedMessage;

public interface IHandlerMessage {

    void sendMessage(AccountCreatedMessage message);
    void readMessage(CustomerCreatedMessage message);

}
