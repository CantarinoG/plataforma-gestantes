package com.cantarino.souza.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;

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
        Admin managedAdmin = this.entityManager.merge(obj);
        this.entityManager.remove(managedAdmin);
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
                .createQuery("FROM Admin", Admin.class)
                .getResultList();

        this.entityManager.close();
        return admins;
    }

}
