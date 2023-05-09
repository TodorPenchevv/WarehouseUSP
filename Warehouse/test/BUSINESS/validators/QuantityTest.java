package test.business.validators;

import app.business.exceptions.NegativeNumberException;
import app.business.validators.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
    @Test
    void validQuantity() {
        assertDoesNotThrow(() -> {
            new Quantity(100).validate();
        });
    }

    @Test
    void invalidQuantity() {
        assertThrows(NegativeNumberException.class, () -> {
            new Quantity(-100).validate();
        });
    }
}