package ru.students.lab.dao;

import ru.students.lab.models.Point;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbsDao implements IDao {
    
    protected Class<?> entityClass;
    @PersistenceContext(name = "points_persistence")
    protected EntityManager entityManager;

    public AbsDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<?>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public <E> void add(E entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<?> getAll() {
        return entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }
}
