package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Relatorio;

public class RelatorioDao implements IDao<Relatorio> {
    private EntityManager entityManager;

    @Override
    public void save(Relatorio obj) {
       this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close(); 
    }

    @Override
    public void update(Relatorio obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();  
    }

    @Override
    public boolean delete(Relatorio obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }

    @Override
    public Relatorio find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Relatorio relatorio = this.entityManager.find(Relatorio.class, id);
        this.entityManager.close();
        return relatorio;
    }

    @Override
    public List<Relatorio> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        List<Relatorio> relatorios = this.entityManager.createQuery(
            "FROM Relatorio r WHERE r.deletadoEm IS NULL",
            Relatorio.class)
            .getResultList();
        this.entityManager.close();
        return relatorios;
    }

    
}
