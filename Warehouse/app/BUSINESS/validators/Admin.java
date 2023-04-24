package app.business.validators;

import app.business.CurrentUser;
import app.business.exceptions.CustomException;
import app.business.exceptions.NotAdminException;

public class Admin implements Validator {
    int roleId = CurrentUser.getInstance().getRoleId();

    @Override
    public void validate() throws CustomException {
        if (roleId != 2) {
            throw new NotAdminException();
        }
    }
}
