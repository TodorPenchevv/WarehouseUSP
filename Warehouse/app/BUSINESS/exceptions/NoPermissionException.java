package app.business.exceptions;

public class NoPermissionException extends CustomException {
    public NoPermissionException() {
        super("Нямаш нужните права за тази операция!");
    }
}
