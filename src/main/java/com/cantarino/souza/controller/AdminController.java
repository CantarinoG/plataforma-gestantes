package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.AdminDao;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.valid.ValidateAdmin;

public class AdminController {

    private AdminDao repositorio;
    private ValidateAdmin validator;

    public AdminController() {
        repositorio = new AdminDao();
        validator = new ValidateAdmin();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMAdmin(repositorio.findAll()), null);
    }

    public void cadastrar(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm) {

        Admin novoAdm = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm);

        repositorio.save(novoAdm);
    }

    public Admin buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void atualizar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm) {
        Admin novoAdm = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm);
        novoAdm.setId(id);
        repositorio.update(novoAdm);
    }

    public void excluir(int id) {
        Admin adm = repositorio.find(id);
        repositorio.delete(adm);
    }

}
