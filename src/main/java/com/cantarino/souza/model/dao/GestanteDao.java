package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
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
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
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
                .createQuery("FROM Gestante g WHERE g.deletadoEm IS NULL", Gestante.class)
                .getResultList();

        this.entityManager.close();
        return gestantes;
    }

    public Gestante findByCpf(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Gestante gestante = this.entityManager
                .createQuery("FROM Gestante g WHERE g.cpf = :cpf AND g.deletadoEm IS NULL", Gestante.class)
                .setParameter("cpf", cpf)
                .getSingleResult();

        this.entityManager.close();
        return gestante;
    }

}
