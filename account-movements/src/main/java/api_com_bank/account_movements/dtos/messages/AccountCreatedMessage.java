package api_com_bank.account_movements.dtos.messages;

public record AccountCreatedMessage(String identificationNumberLastDigits, String accountNumber, String name) {
}
