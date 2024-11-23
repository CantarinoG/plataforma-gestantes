package com.cantarino.souza.controller;

import java.time.LocalDateTime;

import com.cantarino.souza.model.dao.ComentarioDao;
import com.cantarino.souza.model.entities.Comentario;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.valid.ValidateComentario;

public class ComentarioController {
    private ComentarioDao repositorio;
    private ValidateComentario validator;

    public ComentarioController(){
        this.repositorio = new ComentarioDao();
        this.validator = new ValidateComentario();
    }
    public void atualizarTabela(){}

    public void cadastrar(String conteudo, LocalDateTime data, Publicacao publicacao, Usuario autor, LocalDateTime deletadoEm){
        Comentario novoComentario = validator.validaCamposEntrada(conteudo);
        novoComentario.setConteudo(conteudo);
        novoComentario.setAutor(autor);
        novoComentario.setPublicacao(publicacao);
        repositorio.save(novoComentario);
    }
    public void atualizar(int id, String conteudo, LocalDateTime data, Publicacao publicacao, Usuario autor, LocalDateTime deletadoEm){
        Comentario novoComentario = validator.validaCamposEntrada(conteudo);
        novoComentario.setConteudo(conteudo);
        novoComentario.setAutor(autor);
        novoComentario.setPublicacao(publicacao);
        repositorio.update(novoComentario);
    }
    public void excluir(int id){
        Comentario comentario = repositorio.find(id);
        repositorio.delete(comentario);
    }
    
}
