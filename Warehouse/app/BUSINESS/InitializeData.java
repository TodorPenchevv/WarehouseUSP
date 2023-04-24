package app.business;

import app.business.create.InsertRole;
import app.business.create.InsertTransaction;
import app.business.create.InsertUser;
import app.business.repository.UserRepository;
import app.logging.ErrorLogging;
import app.logging.ExceptionToString;
import app.orm.Roles;
import app.orm.Transactions;
import app.orm.User;
import org.hibernate.Session;

import java.util.List;

public class InitializeData {
    public InitializeData() {
        List<User> users = UserRepository.findAll();
        if(users == null || users.isEmpty()) {
            initializeConnection();
            initializeRoles();
            initializeTransactions();
            initializeAdmin();
        }
    }

    private void initializeRoles() {
        InsertRole.create(1, Roles.OPERATOR);
        InsertRole.create(2, Roles.MANAGER);
    }

    private void initializeTransactions() {
        InsertTransaction.create(1, Transactions.SALE);
        InsertTransaction.create(2, Transactions.PURCHASE);
    }

    private void initializeAdmin() {
        try {
            InsertUser.create("Admin", "admin", "Admin123", "Admin123", 2);
        } catch (Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }

    private void initializeConnection(){
        Session session = GetSession.getSession();
        session.beginTransaction();
        session.getTransaction().commit();
        session.close();
    }
}
