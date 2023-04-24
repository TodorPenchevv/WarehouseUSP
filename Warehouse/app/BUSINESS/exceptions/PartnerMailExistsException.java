package app.business.exceptions;

public class PartnerMailExistsException extends CustomException {
    public PartnerMailExistsException() {
        super("Този имейл принадлежи на друга фирма");
    }
}
