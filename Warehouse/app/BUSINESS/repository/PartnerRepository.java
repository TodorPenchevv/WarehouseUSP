package app.business.repository;

import app.business.GetSession;
import app.orm.Partner;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PartnerRepository {
    public static List<Partner> findAll(){
        Session session = GetSession.getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Partner> criteriaQuery = criteriaBuilder.createQuery(Partner.class);
        criteriaQuery.from(Partner.class);

        List<Partner> result = session.createQuery(criteriaQuery).getResultList();

        session.close();
        return result;
    }

    public static List<Partner> findByName(String name) {
        Session session = GetSession.getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Partner> criteriaQuery = criteriaBuilder.createQuery(Partner.class);
        Root<Partner> root = criteriaQuery.from(Partner.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("name"), name));

        List<Partner> result = session.createQuery(criteriaQuery).getResultList();

        session.close();
        return result;
    }

    public static List<Partner> findByMail(String email) {
        Session session = GetSession.getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Partner> criteriaQuery = criteriaBuilder.createQuery(Partner.class);
        Root<Partner> root = criteriaQuery.from(Partner.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("email"), email));

        List<Partner> result = session.createQuery(criteriaQuery).getResultList();

        session.close();
        return result;
    }
}
