package api_com_bank.account_movements.services.contracts.rabbitMQ;

import api_com_bank.account_movements.dtos.messages.AccountCreatedMessage;
import api_com_bank.account_movements.dtos.messages.CustomerCreatedMessage;

public interface IHandlerMessage {

    void sendMessage(AccountCreatedMessage message);
    void readMessage(CustomerCreatedMessage message);

}
