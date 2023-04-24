package app.business.repository;

import app.business.GetSession;
import app.orm.Good;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GoodRepository {
    public static List<Good> findAll(){
        Session session = GetSession.getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Good> criteriaQuery = criteriaBuilder.createQuery(Good.class);
        criteriaQuery.from(Good.class);

        List<Good> result = session.createQuery(criteriaQuery).getResultList();

        session.close();
        return result;
    }

    public static List<Good> findByGood(String good) {
        Session session = GetSession.getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Good> criteriaQuery = criteriaBuilder.createQuery(Good.class);
        Root<Good> root = criteriaQuery.from(Good.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("good"), good));

        List<Good> result = session.createQuery(criteriaQuery).getResultList();

        session.close();
        return result;
    }
}
