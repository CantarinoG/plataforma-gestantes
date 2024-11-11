package com.cantarino.souza.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Secretario;

public class SecretarioDao implements IDao<Secretario> {

    private EntityManager entityManager;

    @Override
    public void save(Secretario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Secretario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public boolean delete(Secretario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        Secretario managedSecretario = this.entityManager.merge(obj);
        this.entityManager.remove(managedSecretario);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Secretario find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Secretario secretario = this.entityManager.find(Secretario.class, id);

        this.entityManager.close();

        return secretario;
    }

    @Override
    public List<Secretario> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Secretario> secretarios = this.entityManager
                .createQuery("FROM Secretario", Secretario.class)
                .getResultList();

        this.entityManager.close();
        return secretarios;
    }

}
