package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Comentario;

public class ComentarioDao implements IDao<Comentario> {
     private EntityManager entityManager;

    @Override
    public void save(Comentario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Comentario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();   
    }

    @Override
    public boolean delete(Comentario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;

    }

    @Override
    public Comentario find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Comentario comentario = this.entityManager.find(Comentario.class, id);
        this.entityManager.close();
        return comentario;
    }

    @Override
    public List<Comentario> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        List<Comentario> comentarios = this.entityManager.createQuery(
            "FROM Comentario c WHERE c.deletadoEm IS NULL",
            Comentario.class)
            .getResultList();
        this.entityManager.close();
        return comentarios; 
    }

     
}
