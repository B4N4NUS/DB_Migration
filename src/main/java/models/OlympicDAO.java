package models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class OlympicDAO {
    public Olympic findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Olympic.class, id);
    }
    public void save(Olympic x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(x);
        tx1.commit();
        session.close();
    }

    public void update(Olympic x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(x);
        tx1.commit();
        session.close();
    }

    public void delete(Olympic x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(x);
        tx1.commit();
        session.close();
    }

    public List<Olympic> findAll() {
        return (List<Olympic>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Olympic a").list();
    }
}
