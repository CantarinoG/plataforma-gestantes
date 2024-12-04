package com.cantarino.souza.model.dao;

import java.util.List;

public interface IDao<T> {

    public void salvar(T obj);

    public void editar(T obj);

    public boolean deletar(T obj);

    public T buscar(int id);

    public List<T> buscarTodos();

}