package app.business.validators;

import app.business.exceptions.CustomException;
import app.business.exceptions.DatesNotConsecutive;

import java.time.LocalDate;

public class DatesConsecutive implements Validator {
    private LocalDate date1;
    private LocalDate date2;

    public DatesConsecutive(LocalDate date1, LocalDate date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    @Override
    public void validate() throws CustomException {
        if(date1.isAfter(date2)) {
            throw new DatesNotConsecutive();
        }
    }
}
