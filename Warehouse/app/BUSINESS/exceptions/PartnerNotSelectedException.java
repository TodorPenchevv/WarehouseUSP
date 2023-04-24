package app.business.exceptions;

public class PartnerNotSelectedException extends CustomException {
    public PartnerNotSelectedException() {
        super("Избери партньор!");
    }
}
