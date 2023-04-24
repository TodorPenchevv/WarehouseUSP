package app.business.repository;

import app.business.GetSession;
import app.business.exceptions.CustomException;
import app.business.validators.DatesConsecutive;
import app.business.validators.DateValidator;
import app.orm.Invoice;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class InvoiceRepository {
    public static List<Invoice> findByPeriod(LocalDate start, LocalDate end) throws CustomException {
        Session session = GetSession.getSession();

        //Validate LocalDate not null
        new DateValidator(start).validate();
        new DateValidator(end).validate();

        //Check if dates are chronologically correct
        new DatesConsecutive(start, end).validate();

        Calendar startDate = new GregorianCalendar(start.getYear(), start.getMonthValue() - 1, start.getDayOfMonth());
        Calendar endDate = new GregorianCalendar(end.getYear(), end.getMonthValue() - 1, end.getDayOfMonth());

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteriaQuery = criteriaBuilder.createQuery(Invoice.class);
        Root<Invoice> root = criteriaQuery.from(Invoice.class);
        criteriaQuery.where(criteriaBuilder.between(root.get("calendar"), startDate, endDate));

        List<Invoice> result = session.createQuery(criteriaQuery).getResultList();

        session.close();
        return result;
    }
}
