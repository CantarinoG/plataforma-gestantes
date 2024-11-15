package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Medico;

public class MedicoDao implements IDao<Medico> {

    private EntityManager entityManager;

    @Override
    public void save(Medico obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Medico obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public boolean delete(Medico obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Medico find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Medico medico = this.entityManager.find(Medico.class, id);

        this.entityManager.close();

        return medico;
    }

    @Override
    public List<Medico> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Medico> medicos = this.entityManager
                .createQuery("FROM Medico m WHERE m.deletadoEm IS NULL", Medico.class)
                .getResultList();

        this.entityManager.close();
        return medicos;
    }

}
