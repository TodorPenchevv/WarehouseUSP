package app.business.validators;

import app.business.exceptions.CustomException;
import app.business.exceptions.InvalidPriceException;

public class Price implements Validator {
    private double price;

    public Price(double price) {
        this.price = price;
    }

    public void validate() throws CustomException {
        if(negativePrice() || invalidPrice()) {
            throw new InvalidPriceException(0, 1000000);
        }
    }

    private boolean negativePrice() {
        return price < 0;
    }

    private boolean invalidPrice() {
        return price > 100000;
    }
}
