package ru.students.lab.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.students.lab.models.Point;
import ru.students.lab.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class PointDao {

    public List<Point> getPoints() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Point", Point.class).list();
        }
    }

    public void add(Point newPoint) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            session.saveOrUpdate(newPoint);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
