package com.cantarino.souza.controller;


import java.time.LocalDateTime;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.PublicacaoDao;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.valid.ValidatePublicacao;

public class PublicacaoController {
    private PublicacaoDao repositorio;
    private ValidatePublicacao validator;

    public void atualizarTabela(JTable grd){
        Util.jTableShow(grd,new TMPublicacao(repositorio.findAll()),null);
    }

    public PublicacaoController(){
        this.repositorio = new PublicacaoDao();
        this.validator = new ValidatePublicacao();
    }
    public void atualizarTabela(){}
    public void cadastrar(String titulo, String corpo, LocalDateTime data, boolean isMedico, Usuario autor, LocalDateTime deletadoEm){
        Publicacao novaPublicacao = validator.validaCamposEntrada(titulo, corpo, String.valueOf(isMedico));
        novaPublicacao.setAutor(autor);
        novaPublicacao.setCorpo(corpo);
        novaPublicacao.setMedico(isMedico);
        repositorio.save(novaPublicacao);
    }
    public void atualizar(int id,String titulo, String corpo, LocalDateTime data, boolean isMedico, Usuario autor, LocalDateTime deletadoEm){
        Publicacao novaPublicacao = validator.validaCamposEntrada(titulo, corpo, String.valueOf(isMedico));
        novaPublicacao.setId(id);
        novaPublicacao.setAutor(autor);
        novaPublicacao.setCorpo(corpo);
        novaPublicacao.setMedico(isMedico);
        repositorio.update(novaPublicacao);
    }
    public void excluir(int id){
        Publicacao publicacao = repositorio.find(id);
        repositorio.delete(publicacao);
    }
    
}
