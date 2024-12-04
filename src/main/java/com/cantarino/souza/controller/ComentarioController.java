package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMComentario;
import com.cantarino.souza.model.dao.ComentarioDao;
import com.cantarino.souza.model.entities.Comentario;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.valid.ValidateComentario;

public class ComentarioController {
    private ComentarioDao repositorio;
    private ValidateComentario validador;

    public ComentarioController() {
        this.repositorio = new ComentarioDao();
        this.validador = new ValidateComentario();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMComentario(repositorio.buscarTodos()), null);
    }

    public List<Comentario> buscarPorPublicacao(int id) {
        return repositorio.buscarPorPublicacao(id);
    }

    public void salvar(String conteudo, LocalDateTime data, boolean isAnonimo, Publicacao publicacao, Usuario autor,
            LocalDateTime deletadoEm) {

        Comentario novoComentario = validador.validaCamposEntrada(conteudo, data, isAnonimo, autor, publicacao);
        repositorio.salvar(novoComentario);
    }

    public void editar(int id, String conteudo, LocalDateTime data, boolean isAnonimo, Publicacao publicacao,
            Usuario autor,
            LocalDateTime deletadoEm) {

        Comentario novoComentario = validador.validaCamposEntrada(conteudo, data, isAnonimo, autor, publicacao);
        novoComentario.setId(id);
        repositorio.editar(novoComentario);
    }

    public void deletar(int id) {
        Comentario comentario = repositorio.buscar(id);
        repositorio.deletar(comentario);
    }

}
