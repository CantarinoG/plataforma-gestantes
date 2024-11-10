package com.cantarino.souza.model.dao;

import java.util.List;

public interface IDao<T> {

    public void save(T obj);

    public void update(T obj);

    public boolean delete(T obj);

    public T find(int id);

    public List<T> findAll();

}