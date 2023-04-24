package app.business.exceptions;

public class GoodAlreadyExistsException extends CustomException {
    public GoodAlreadyExistsException(String goodName) {
        super("Стока \"" + goodName + "\" вече съществува!");
    }
}
