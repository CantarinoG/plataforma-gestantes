package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMPublicacao;
import com.cantarino.souza.model.dao.PublicacaoDao;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.valid.ValidatePublicacao;

public class PublicacaoController {
    private PublicacaoDao repositorio;
    private ValidatePublicacao validador;

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMPublicacao(repositorio.buscarTodos()), TMPublicacao.getCustomRenderer());
    }

    public PublicacaoController() {
        this.repositorio = new PublicacaoDao();
        this.validador = new ValidatePublicacao();
    }

    public void atualizarTabelaPorGestante(JTable grd, int id) {
        Util.jTableShow(grd, new TMPublicacao(repositorio.buscarPorGestante(id)), null);
    }

    public void salvar(String titulo, String corpo, Boolean isAnonimo, LocalDateTime data, Usuario autor,
            LocalDateTime deletadoEm) {

        Publicacao novaPublicacao = validador.validaCamposEntrada(titulo, corpo, autor, data, isAnonimo);
        repositorio.salvar(novaPublicacao);
    }

    public void editar(int id, String titulo, String corpo, Boolean isAnonimo, LocalDateTime data, Usuario autor,
            LocalDateTime deletadoEm) {

        Publicacao novaPublicacao = validador.validaCamposEntrada(titulo, corpo, autor, data, isAnonimo);
        novaPublicacao.setId(id);

        repositorio.editar(novaPublicacao);
    }

    public Publicacao buscar(int id) {
        return repositorio.buscar(id);
    }

    public List<Publicacao> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void deletar(int id) {
        Publicacao publicacao = repositorio.buscar(id);
        repositorio.deletar(publicacao);
    }

}
