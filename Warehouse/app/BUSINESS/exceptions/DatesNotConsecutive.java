package app.business.exceptions;

public class DatesNotConsecutive extends CustomException {
    public DatesNotConsecutive() {
        super("Датите трябва да са последователни!");
    }
}
