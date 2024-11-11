package com.cantarino.souza.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Gestante;

public class GestanteDao implements IDao<Gestante> {

    private EntityManager entityManager;

    @Override
    public void save(Gestante obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Gestante obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();

    }

    @Override
    public boolean delete(Gestante obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        Gestante managedGestante = this.entityManager.merge(obj);
        this.entityManager.remove(managedGestante);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Gestante find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Gestante gestante = this.entityManager.find(Gestante.class, id);

        this.entityManager.close();

        return gestante;

    }

    @Override
    public List<Gestante> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Gestante> gestantes = this.entityManager
                .createQuery("FROM Gestante", Gestante.class)
                .getResultList();

        this.entityManager.close();
        return gestantes;
    }

}
