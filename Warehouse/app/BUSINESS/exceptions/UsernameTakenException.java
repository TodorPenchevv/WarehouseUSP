package app.business.exceptions;

public class UsernameTakenException extends CustomException {
    public UsernameTakenException() {
        super("Потребителското име е вече заето!");
    }
}
