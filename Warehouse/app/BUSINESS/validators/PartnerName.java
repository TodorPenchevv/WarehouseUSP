package app.business.validators;

import app.business.repository.PartnerRepository;
import app.business.exceptions.CustomException;
import app.business.exceptions.PartnerAlreadyExistsException;
import app.orm.Partner;

import java.util.List;

public class PartnerName implements Validator {
    private String name;

    public PartnerName(String name) {
        this.name = name;
    }

    public void validate() throws CustomException {
        if(unique()) {
            throw new PartnerAlreadyExistsException();
        }
    }

    private boolean unique() {
        List<Partner> partners = PartnerRepository.findByName(name);
        return !partners.isEmpty();
    }
}
