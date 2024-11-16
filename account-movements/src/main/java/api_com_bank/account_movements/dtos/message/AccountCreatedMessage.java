package api_com_bank.account_movements.dtos.message;

public record AccountCreatedMessage(String identificationNumberLastDigits, String accountNumber, String name) {
}
