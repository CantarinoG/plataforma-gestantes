package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Publicacao;

public class PublicacaoDao implements IDao<Publicacao> {
    private EntityManager entityManager;

    @Override
    public void salvar(Publicacao obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void editar(Publicacao obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    @Override
    public boolean deletar(Publicacao obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        obj.setDeletadoEm(LocalDateTime.now());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }

    @Override
    public Publicacao buscar(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Publicacao publicacao = this.entityManager.find(Publicacao.class, id);
        this.entityManager.close();
        return publicacao;
    }

    @Override
    public List<Publicacao> buscarTodos() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        List<Publicacao> publicacoes = this.entityManager.createQuery(
                "FROM Publicacao p WHERE p.deletadoEm IS NULL ORDER BY p.data DESC",
                Publicacao.class)
                .getResultList();
        this.entityManager.close();
        return publicacoes;
    }

    public List<Publicacao> buscarPorGestante(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        List<Publicacao> publicacoes = this.entityManager.createQuery(
                "FROM Publicacao p WHERE p.deletadoEm IS NULL AND p.autor.id = :id ORDER BY p.data DESC",
                Publicacao.class)
                .setParameter("id", id)
                .getResultList();
        this.entityManager.close();
        return publicacoes;
    }
}
