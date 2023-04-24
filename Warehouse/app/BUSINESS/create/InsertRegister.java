package app.business.create;

import app.business.GetSession;
import app.business.exceptions.CustomException;
import app.business.exceptions.RegistryAlreadyExists;
import app.business.repository.RegisterRepository;
import app.business.validators.Price;
import app.orm.Register;
import org.hibernate.Session;

public class InsertRegister {
    public static void create(int id, double balance) throws CustomException {
        Session session = GetSession.getSession();

        new Price(balance).validate();
        if(RegisterRepository.getRegister() != null) throw new RegistryAlreadyExists();

        Register register = new Register(id, balance);

        session.beginTransaction();
        session.save(register);
        session.getTransaction().commit();
        session.close();
    }
}
