package com.cantarino.souza.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Gestante;

public class ExameDao implements IDao<Exame> {

    private EntityManager entityManager;

    @Override
    public void save(Exame obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Exame obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public boolean delete(Exame obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        Exame managedExame = this.entityManager.merge(obj);
        this.entityManager.remove(managedExame);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Exame find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Exame exame = this.entityManager.find(Exame.class, id);

        this.entityManager.close();

        return exame;

    }

    @Override
    public List<Exame> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Exame> exames = this.entityManager
                .createQuery("FROM Exame", Exame.class)
                .getResultList();

        this.entityManager.close();
        return exames;
    }

}
