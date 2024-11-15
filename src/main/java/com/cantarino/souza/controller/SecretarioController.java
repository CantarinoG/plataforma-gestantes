package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.SecretarioDao;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.valid.ValidateSecretario;

public class SecretarioController {

    private SecretarioDao repositorio;
    private ValidateSecretario validator;

    public SecretarioController() {
        repositorio = new SecretarioDao();
        validator = new ValidateSecretario();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMSecretario(repositorio.findAll()), null);
    }

    public void cadastrar(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String dataContratacao) {

        Secretario novoSecretario = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone,
                endereco, deletadoEm, dataContratacao);

        repositorio.save(novoSecretario);

    }

    public Secretario buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void atualizar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String dataContratacao) {
        Secretario novoSecretario = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone,
                endereco, deletadoEm, dataContratacao);
        novoSecretario.setId(id);
        repositorio.update(novoSecretario);
    }

    public void excluir(int id) {
        Secretario secretario = repositorio.find(id);
        repositorio.delete(secretario);
    }

}
