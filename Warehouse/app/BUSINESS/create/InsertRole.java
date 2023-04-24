package app.business.create;

import app.business.GetSession;
import app.orm.Role;
import app.orm.Roles;
import org.hibernate.Session;

public class InsertRole {
    public static void create(int id, Roles role) {
        Session session = GetSession.getSession();

        Role newRole = new Role(id, role);

        session.beginTransaction();
        session.save(newRole);
        session.getTransaction().commit();
        session.close();
    }
}
