package api_com_bank.account_movements.exceptions;

public class ClientErrorException extends RuntimeException {

    public ClientErrorException(String message) {
        super(message);
    }

}
