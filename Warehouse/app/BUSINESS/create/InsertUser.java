package app.business.create;

import app.business.GetSession;
import app.business.validators.Password;
import app.business.validators.Username;
import app.orm.Role;
import app.orm.User;
import org.hibernate.Session;

public class InsertUser {
    public static void create(String name, String username, String password, String passwordConfirm, int roleID) throws Exception {
        Session session = GetSession.getSession();

        //Data validation
        new Password(password, passwordConfirm).validate();
        new Username(username).validate();

        //Creating the user object with the data
        //Some sort of data validation before this step...
        User newUser = new User(name, username, password);

        //Transaction begins now, because we need to update "ROLES" object with PK "roleID" in order
        //to create the FK column in the new row we are currently creating with said "roleID" PK
        session.beginTransaction();

        Role role = session.get(Role.class, roleID);
        newUser.setRole(role);
        role.getUsers().add(newUser);

        //Updating row "role" and saving the new row "user"
        session.save(newUser);
        session.update(role);

        session.getTransaction().commit();
        session.close();
    }
}
