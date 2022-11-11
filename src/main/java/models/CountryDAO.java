package models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CountryDAO {
    public Country findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Country.class, id);
    }
    public void save(Country country) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(country);
        tx1.commit();
        session.close();
    }

    public void update(Country user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(Country user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<Country> findAll() {
        return (List<Country>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Country a").list();
    }
}
