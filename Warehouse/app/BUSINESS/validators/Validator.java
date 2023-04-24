package app.business.validators;

import app.business.exceptions.CustomException;

public interface Validator {
    void validate() throws CustomException;
}
