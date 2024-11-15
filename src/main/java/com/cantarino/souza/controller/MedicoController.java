package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.MedicoDao;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.valid.ValidateMedico;

public class MedicoController {

    private MedicoDao repositorio;
    private ValidateMedico validator;

    public MedicoController() {
        repositorio = new MedicoDao();
        validator = new ValidateMedico();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMMedico(repositorio.findAll()), null);
    }

    public void cadastrar(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String especializacao, String crm) {

        Medico novoMedico = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm, especializacao, crm);

        repositorio.save(novoMedico);

    }

    public Medico buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void atualizar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String especializacao, String crm) {
        Medico novoMedico = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm, especializacao, crm);
        novoMedico.setId(id);
        repositorio.update(novoMedico);
    }

    public void excluir(int id) {
        Medico medico = repositorio.find(id);
        repositorio.delete(medico);
    }

}
