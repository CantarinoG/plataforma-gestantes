package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Secretario;

public class SecretarioDao implements IDao<Secretario> {

    private EntityManager entityManager;

    @Override
    public void salvar(Secretario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void editar(Secretario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public boolean deletar(Secretario obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Secretario buscar(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Secretario secretario = this.entityManager.find(Secretario.class, id);

        this.entityManager.close();

        return secretario;
    }

    @Override
    public List<Secretario> buscarTodos() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Secretario> secretarios = this.entityManager
                .createQuery("FROM Secretario s WHERE s.deletadoEm IS NULL ORDER BY LOWER(s.nome)", Secretario.class)
                .getResultList();

        this.entityManager.close();
        return secretarios;
    }

    public Secretario buscarPorCpf(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Secretario secretario = this.entityManager
                .createQuery("FROM Secretario s WHERE s.cpf = :cpf AND s.deletadoEm IS NULL", Secretario.class)
                .setParameter("cpf", cpf)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        this.entityManager.close();
        return secretario;
    }

    public Secretario buscarPorEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Secretario secretario = this.entityManager
                .createQuery("FROM Secretario s WHERE s.email = :email AND s.deletadoEm IS NULL", Secretario.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        this.entityManager.close();
        return secretario;
    }

}
