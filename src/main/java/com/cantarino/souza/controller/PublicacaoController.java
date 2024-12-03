package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMPublicacao;
import com.cantarino.souza.model.dao.PublicacaoDao;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.PublicacaoException;
import com.cantarino.souza.model.valid.ValidatePublicacao;

public class PublicacaoController {
    private PublicacaoDao repositorio;
    private ValidatePublicacao validator;

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMPublicacao(repositorio.findAll()), TMPublicacao.getCustomRenderer());
    }

    public PublicacaoController() {
        this.repositorio = new PublicacaoDao();
        this.validator = new ValidatePublicacao();
    }

    public void filtrarTabelaPorIdGestante(JTable grd, int id) {
        Util.jTableShow(grd, new TMPublicacao(repositorio.filterGestanteId(id)), null);
    }

    public void cadastrar(String titulo, String corpo, Boolean isAnonimo, LocalDateTime data, Usuario autor,
            LocalDateTime deletadoEm) {
        if (autor == null) {
            throw new PublicacaoException("Autor não pode ser nulo");
        }
        if (data == null) {
            throw new PublicacaoException("Data não pode ser nula");
        }

        Publicacao novaPublicacao = validator.validaCamposEntrada(titulo, corpo);
        novaPublicacao.setAutor(autor);
        novaPublicacao.setData(data);
        novaPublicacao.setAnonimo(isAnonimo);
        repositorio.save(novaPublicacao);
    }

    public void atualizar(int id, String titulo, String corpo, Boolean isAnonimo, LocalDateTime data, Usuario autor,
            LocalDateTime deletadoEm) {
        if (autor == null) {
            throw new PublicacaoException("Autor não pode ser nulo");
        }
        if (data == null) {
            throw new PublicacaoException("Data não pode ser nula");
        }

        Publicacao novaPublicacao = validator.validaCamposEntrada(titulo, corpo);
        novaPublicacao.setId(id);
        novaPublicacao.setAutor(autor);
        novaPublicacao.setData(data);
        novaPublicacao.setAnonimo(isAnonimo);
        repositorio.update(novaPublicacao);
    }

    public Publicacao buscarPorId(int id) {
        return repositorio.find(id);
    }

    public List<Publicacao> buscarTodos() {
        return repositorio.findAll();
    }

    public void excluir(int id) {
        Publicacao publicacao = repositorio.find(id);
        repositorio.delete(publicacao);
    }

}
