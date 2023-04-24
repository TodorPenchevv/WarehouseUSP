package app.business.repository;

import app.business.GetSession;
import app.orm.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepository {
    public static User findById(int id) {
        Session session = GetSession.getSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public static List<User> findByUsername(String username) {
        Session session = GetSession.getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("username"), username));

        List<User> result = session.createQuery(criteriaQuery).getResultList();

        session.close();
        return result;
    }

    public static List<User> findAll(){
        Session session = GetSession.getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        criteriaQuery.from(User.class);

        List<User> result = session.createQuery(criteriaQuery).getResultList();

        session.close();
        return result;
    }
}
