package ru.students.lab.dao;

import java.util.List;

public interface IDao {
    <E> void add(E entity);
    List<?> getAll();
}
