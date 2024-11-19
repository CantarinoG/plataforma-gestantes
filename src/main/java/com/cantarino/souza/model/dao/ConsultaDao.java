package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Consulta;

public class ConsultaDao implements IDao<Consulta> {

    private EntityManager entityManager;

    @Override
    public void save(Consulta obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Consulta obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public boolean delete(Consulta obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
        return true;
    }

    @Override
    public Consulta find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        Consulta consulta = this.entityManager.find(Consulta.class, id);

        this.entityManager.close();

        return consulta;
    }

    @Override
    public List<Consulta> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Consulta> consultas = this.entityManager
                .createQuery("FROM Consulta c WHERE c.deletadoEm IS NULL", Consulta.class)
                .getResultList();

        this.entityManager.close();
        return consultas;
    }

    public List<Consulta> filterMedicoId(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Consulta> consultas = this.entityManager
                .createQuery("FROM Consulta c WHERE c.deletadoEm IS NULL AND c.medico.id = :id", Consulta.class)
                .setParameter("id", id)
                .getResultList();

        this.entityManager.close();
        return consultas;
    }

    public List<Consulta> filterGestanteId(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Consulta> consultas = this.entityManager
                .createQuery("FROM Consulta c WHERE c.deletadoEm IS NULL AND c.paciente.id = :id", Consulta.class)
                .setParameter("id", id)
                .getResultList();

        this.entityManager.close();
        return consultas;
    }

    public List<Consulta> filterMedicoIdStatus(int id, String status) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Consulta> consultas = this.entityManager
                .createQuery(
                        "FROM Consulta c WHERE c.deletadoEm IS NULL AND c.medico.id = :id AND c.status = :status",
                        Consulta.class)
                .setParameter("id", id)
                .setParameter("status", status)
                .getResultList();

        this.entityManager.close();
        return consultas;
    }

    public List<Consulta> filterGestanteIdStatus(int id, String status) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        List<Consulta> consultas = this.entityManager
                .createQuery(
                        "FROM Consulta c WHERE c.deletadoEm IS NULL AND c.paciente.id = :id AND c.status = :status",
                        Consulta.class)
                .setParameter("id", id)
                .setParameter("status", status)
                .getResultList();

        this.entityManager.close();
        return consultas;
    }

}
