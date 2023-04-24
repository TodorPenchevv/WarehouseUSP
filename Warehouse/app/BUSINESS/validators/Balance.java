package app.business.validators;

import app.business.exceptions.CustomException;
import app.business.exceptions.NotEnoughMoneyException;
import app.business.exceptions.RegistryEmptyException;
import app.business.repository.RegisterRepository;
import app.orm.Good;
import app.orm.Register;

import java.util.List;

public class Balance implements Validator {
    List<Good> goods;

    public Balance(List<Good> goods) {
        this.goods = goods;
    }

    @Override
    public void validate() throws CustomException {
        double balance = 0;

        for(Good good : this.goods) {
            balance += good.getQuantity() * good.getPrice();
        }

        Register register = RegisterRepository.getRegister();

        if(register == null) {
            throw new RegistryEmptyException();
        }

        if(register.getBalance() < balance) {
            throw new NotEnoughMoneyException();
        }
    }
}
