package app.business.exceptions;

public class NotEnoughQuantityException extends CustomException {
    public NotEnoughQuantityException(String good, int quantity) {
        super("Няма достатъчна наличност от " + good + "! Налични са само " + quantity + " бройки");
    }
}
