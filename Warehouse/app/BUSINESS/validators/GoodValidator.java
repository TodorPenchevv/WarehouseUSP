package app.business.validators;

import app.business.exceptions.CustomException;
import app.business.exceptions.GoodAlreadyExistsException;
import app.business.repository.GoodRepository;
import app.orm.Good;

import java.util.List;

public class GoodValidator implements Validator {
    private String good;

    public GoodValidator(String good) {
        this.good = good;
    }

    public void validate() throws CustomException {
        if(goodExists()) {
            throw new GoodAlreadyExistsException(this.good);
        }
    }

    private boolean goodExists() {
        List<Good> goods = GoodRepository.findByGood(good);
        return (goods != null && !goods.isEmpty());
    }
}
