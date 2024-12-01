package com.cantarino.souza.factory;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cantarino.souza.model.entities.Admin;

public class DatabaseJPA {

    private EntityManagerFactory factory;

    private static DatabaseJPA INSTANCE = null;

    public static DatabaseJPA getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseJPA();
            INSTANCE.initializeData();
        }

        return INSTANCE;
    }

    private DatabaseJPA() {
        factory = Persistence.createEntityManagerFactory("bem-gestar");
    }

    private void initializeData() {
        EntityManager em = getEntityManager();
        try {
            try {
                em.createQuery("FROM Admin a WHERE a.cpf = :cpf AND a.deletadoEm IS NULL", Admin.class)
                        .setParameter("cpf", "00000000000")
                        .getSingleResult();
            } catch (javax.persistence.NoResultException e) {
                Admin admin = new Admin("00000000000", "root", "",
                        "$2a$10$0X8SJRAa4PSIagVNXUCU0.fIGyYFJUkvVqb6POYbbQcwRj824RsMK", LocalDate.now(), "", "", null);
                // Senha padrão: 000000
                em.getTransaction().begin();
                em.persist(admin);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public void closeFactory() {
        this.factory.close();
    }

}
