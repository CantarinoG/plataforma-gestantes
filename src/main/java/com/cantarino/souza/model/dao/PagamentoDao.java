package com.cantarino.souza.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Pagamento;
import java.time.LocalDateTime;

public class PagamentoDao implements IDao<Pagamento> {

    private EntityManager entityManager;

    @Override
    public void save(Pagamento obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void update(Pagamento obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    @Override
    public boolean delete(Pagamento obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }

    @Override
    public Pagamento find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Pagamento pagamento = this.entityManager.find(Pagamento.class, id);
        this.entityManager.close();
        return pagamento;
    }

    @Override
    public List<Pagamento> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        List<Pagamento> pagamentos = this.entityManager.createQuery(
                "FROM Pagamento p WHERE p.deletadoEm IS NULL ORDER BY p.id DESC",
                Pagamento.class)
                .getResultList();
        this.entityManager.close();
        return pagamentos;
    }

    public List<Pagamento> filterGestanteId(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        List<Pagamento> pagamentos = this.entityManager.createQuery(
                "FROM Pagamento p WHERE p.deletadoEm IS NULL AND p.paciente.id = :id",
                Pagamento.class)
                .setParameter("id", id)
                .getResultList();
        this.entityManager.close();
        return pagamentos;
    }

}
