package com.cantarino.souza.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Consulta;

public class ConsultaDao implements IDao<Consulta> {

    private EntityManager entityManager;

    @Override
    public void save(Consulta obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Consulta obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public boolean delete(Consulta obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        Consulta managedConsulta = this.entityManager.merge(obj);
        this.entityManager.remove(managedConsulta);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Consulta find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Consulta consulta = this.entityManager.find(Consulta.class, id);

        this.entityManager.close();

        return consulta;
    }

    @Override
    public List<Consulta> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Consulta> consultas = this.entityManager
                .createQuery("FROM Consulta", Consulta.class)
                .getResultList();

        this.entityManager.close();
        return consultas;
    }

}
