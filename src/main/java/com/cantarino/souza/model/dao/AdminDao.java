package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Admin;

public class AdminDao implements IDao<Admin> {

    private EntityManager entityManager;

    @Override
    public void save(Admin obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Admin obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public boolean delete(Admin obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Admin find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Admin admin = this.entityManager.find(Admin.class, id);

        this.entityManager.close();

        return admin;
    }

    @Override
    public List<Admin> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Admin> admins = this.entityManager
                .createQuery("FROM Admin a WHERE a.deletadoEm IS NULL", Admin.class)
                .getResultList();

        this.entityManager.close();
        return admins;
    }

}
