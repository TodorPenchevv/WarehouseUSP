package app.business.exceptions;

public class NotEnoughMoneyException extends CustomException {
    public NotEnoughMoneyException() {
        super("В касата няма достатъчно пари!");
    }
}
