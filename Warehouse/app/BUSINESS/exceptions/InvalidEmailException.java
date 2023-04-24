package app.business.exceptions;

public class InvalidEmailException extends CustomException {
    public InvalidEmailException() {
        super("Въведеният имейл е в невалиден формат!");
    }
}
