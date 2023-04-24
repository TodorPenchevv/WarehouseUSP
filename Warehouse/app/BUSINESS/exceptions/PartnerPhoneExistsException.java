package app.business.exceptions;

public class PartnerPhoneExistsException extends CustomException {
    public PartnerPhoneExistsException() {
        super("Телефонният номер принадлежи на друг партньор!");
    }
}
