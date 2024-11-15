package api_com_bank.customer.services.contracts;

import api_com_bank.customer.dtos.message.CustomerCreatedMessage;

public interface IHandlerMessage {

    void sendMessage(CustomerCreatedMessage message);
}
