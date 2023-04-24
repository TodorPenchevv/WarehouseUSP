package app.business.exceptions;

public class InvalidDateException extends CustomException {
    public InvalidDateException() {
        super("Въведените дати са в невалиден формат!");
    }
}
