package app.business.exceptions;

public class InvalidPassword extends CustomException {
    public InvalidPassword() {
        super("Паролата трябва да е поне 8 символа, да съдържа поне една главна буква и едно число!");
    }
}
