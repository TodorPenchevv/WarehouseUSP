package app.business.exceptions;

public class InvalidPhoneNumberException extends CustomException {
    public InvalidPhoneNumberException() {
        super("Телефонът е в невалиден формат!");
    }
}
