package models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.query.Query;

import java.util.List;

public class EventDAO {
    public Event findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Event.class, id);
    }
    public void save(Event x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(x);
        tx1.commit();
        session.close();
    }

    public void update(Event x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(x);
        tx1.commit();
        session.close();
    }

    public void delete(Event x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(x);
        tx1.commit();
        session.close();
    }

    public List<Event> findAll() {
        return (List<Event>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Event a").list();
    }
}
