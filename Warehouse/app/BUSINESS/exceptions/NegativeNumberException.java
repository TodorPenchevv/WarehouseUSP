package app.business.exceptions;

public class NegativeNumberException extends CustomException {
    public NegativeNumberException(String item) {
        super(item + " трябва да е положително число!");
    }
}
