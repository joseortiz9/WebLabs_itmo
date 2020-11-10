package ru.students.lab.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.students.lab.models.Point;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "pointDao")
@SessionScoped
public class PointDao {

    @PersistenceContext(unitName = "pointsManagement")
    private EntityManager entityManager;

    public List<Point> getPoints() {
        return getEntityManager().createQuery("from Point", Point.class).getResultList();
    }

    public void add(Point newPoint) {
        entityManager.persist(newPoint);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}