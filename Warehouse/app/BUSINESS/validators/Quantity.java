package app.business.validators;

import app.business.exceptions.NegativeNumberException;
import app.business.exceptions.CustomException;

public class Quantity implements Validator {
    private int quantity;

    public Quantity(int quantity) {
        this.quantity = quantity;
    }

    public void validate() throws CustomException {
        if(negativeQuantity()) {
            throw new NegativeNumberException("Количеството");
        }
    }

    private boolean negativeQuantity() {
        return quantity < 0;
    }
}
