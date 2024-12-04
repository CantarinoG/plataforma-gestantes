package com.cantarino.souza.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Pagamento;
import java.time.LocalDateTime;

public class PagamentoDao implements IDao<Pagamento> {

    private EntityManager entityManager;

    @Override
    public void salvar(Pagamento obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void editar(Pagamento obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    @Override
    public boolean deletar(Pagamento obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }

    @Override
    public Pagamento buscar(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Pagamento pagamento = this.entityManager.find(Pagamento.class, id);
        this.entityManager.close();
        return pagamento;
    }

    @Override
    public List<Pagamento> buscarTodos() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        List<Pagamento> pagamentos = this.entityManager.createQuery(
                "FROM Pagamento p WHERE p.deletadoEm IS NULL ORDER BY p.id DESC",
                Pagamento.class)
                .getResultList();
        this.entityManager.close();
        return pagamentos;
    }

    public List<Pagamento> buscarPorGestante(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        List<Pagamento> pagamentos = this.entityManager.createQuery(
                "FROM Pagamento p WHERE p.deletadoEm IS NULL AND p.paciente.id = :id",
                Pagamento.class)
                .setParameter("id", id)
                .getResultList();
        this.entityManager.close();
        return pagamentos;
    }

    public Pagamento buscarPorProcedimento(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Pagamento pagamento = this.entityManager.createQuery(
                "FROM Pagamento p WHERE p.deletadoEm IS NULL AND p.procedimento.id = :id",
                Pagamento.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst().orElse(null);
        this.entityManager.close();
        return pagamento;
    }

}
