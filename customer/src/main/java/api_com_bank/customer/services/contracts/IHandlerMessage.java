package api_com_bank.customer.services.contracts;

public interface IHandlerMessage {

    void sendMessage(Object response);
    void readMessage(String identificationId);
}
