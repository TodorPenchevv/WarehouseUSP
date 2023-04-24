package app.business.validators;

import app.business.exceptions.CustomException;
import app.business.exceptions.InvalidPassword;
import app.business.exceptions.PasswordsNotMatchException;

public class Password implements Validator {
    private String password;
    private String passwordConfirm;

    public Password(String password, String passwordConfirm) {
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public void validate() throws CustomException {
        if(passwordTooShort() || passwordChars()) {
            throw new InvalidPassword();
        }
        if(passwordNotMatchConfirm()) {
            throw new PasswordsNotMatchException();
        }
    }

    private boolean passwordTooShort() {
        return password.length() < 8;
    }

    private boolean passwordChars() {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        for(int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag)
                return false;
        }
        return true;
    }

    private boolean passwordNotMatchConfirm() {
        return !password.equals(passwordConfirm);
    }

}
