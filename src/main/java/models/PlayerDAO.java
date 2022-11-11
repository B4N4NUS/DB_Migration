package models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class PlayerDAO {
    public Player findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Player.class, id);
    }
    public void save(Player x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(x);
        tx1.commit();
        session.close();
    }

    public void update(Player x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(x);
        tx1.commit();
        session.close();
    }

    public void delete(Player x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(x);
        tx1.commit();
        session.close();
    }

    public List<Player> findAll() {
        return (List<Player>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Player a").list();
    }
}
