package models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ResultDAO {
    public Result findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Result.class, id);
    }
    public void save(Result x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(x);
        tx1.commit();
        session.close();
    }

    public void update(Result x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(x);
        tx1.commit();
        session.close();
    }

    public void delete(Result x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(x);
        tx1.commit();
        session.close();
    }

    public List<Result> findAll() {
        return (List<Result>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Result a").list();
    }
}
