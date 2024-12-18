package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Exame;

public class ExameDao implements IDao<Exame> {

    private EntityManager entityManager;

    @Override
    public void salvar(Exame obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void editar(Exame obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public boolean deletar(Exame obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Exame buscar(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Exame exame = this.entityManager.find(Exame.class, id);

        this.entityManager.close();

        return exame;

    }

    @Override
    public List<Exame> buscarTodos() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Exame> exames = this.entityManager
                .createQuery("FROM Exame e WHERE e.deletadoEm IS NULL ORDER BY e.data DESC", Exame.class)
                .getResultList();

        this.entityManager.close();
        return exames;
    }

    public List<Exame> buscarPorGestante(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Exame> consultas = this.entityManager
                .createQuery("FROM Exame e WHERE e.deletadoEm IS NULL AND e.paciente.id = :id ORDER BY e.data DESC",
                        Exame.class)
                .setParameter("id", id)
                .getResultList();

        this.entityManager.close();
        return consultas;
    }

    public List<Exame> buscarPorMedico(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Exame> exames = this.entityManager
                .createQuery("FROM Exame e WHERE e.deletadoEm IS NULL AND e.medico.id = :id ORDER BY e.data DESC",
                        Exame.class)
                .setParameter("id", id)
                .getResultList();

        this.entityManager.close();
        return exames;
    }

    public List<Exame> buscarPorGestanteEStatus(int id, String status) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Exame> consultas = this.entityManager
                .createQuery(
                        "FROM Exame e WHERE e.deletadoEm IS NULL AND e.paciente.id = :id AND e.status = :status ORDER BY e.data DESC",
                        Exame.class)
                .setParameter("id", id)
                .setParameter("status", status)
                .getResultList();

        this.entityManager.close();
        return consultas;
    }

    public List<Exame> buscarPorMedicoEStatus(int id, String status) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Exame> exames = this.entityManager
                .createQuery(
                        "FROM Exame e WHERE e.deletadoEm IS NULL AND e.medico.id = :id AND e.status = :status ORDER BY e.data DESC",
                        Exame.class)
                .setParameter("id", id)
                .setParameter("status", status)
                .getResultList();

        this.entityManager.close();
        return exames;
    }

    public List<Exame> buscarPorNomeGestante(String substring) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Exame> exames = this.entityManager
                .createQuery(
                        "FROM Exame e WHERE e.deletadoEm IS NULL AND LOWER(e.paciente.nome) LIKE LOWER(:substring) ORDER BY e.data DESC",
                        Exame.class)
                .setParameter("substring", substring + "%")
                .getResultList();

        this.entityManager.close();
        return exames;
    }

}
