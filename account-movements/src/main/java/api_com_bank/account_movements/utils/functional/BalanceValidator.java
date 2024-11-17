package api_com_bank.account_movements.utils.functional;

@FunctionalInterface
public interface BalanceValidator {
    boolean isValid(Double currentBalance, Double movementValue);
}
