package app.business.exceptions;

public class InvalidPriceException extends CustomException {
    public InvalidPriceException(int min, int max) {
        super("Цената трябва да е между " + min + " и " + max + "!");
    }
}
