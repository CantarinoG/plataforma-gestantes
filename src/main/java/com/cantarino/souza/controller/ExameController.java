package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.ExameDao;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.valid.ValidateExame;

public class ExameController {

    private ExameDao repositorio;
    private ValidateExame validator;

    public ExameController() {
        repositorio = new ExameDao();
        validator = new ValidateExame();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMExame(repositorio.findAll()), null);
    }

    public void cadastrar(Gestante paciente, String descricao, String data, String valor, String status,
            Relatorio relatorio, String deletadoEm, String dataResultado, Usuario requisitadoPor) {

        Exame novoExame = validator.validaCamposEntrada(descricao, data, valor, status, deletadoEm, dataResultado,
                dataResultado);
        novoExame.setPaciente(paciente);
        novoExame.setRequisitadoPor(requisitadoPor);
        repositorio.save(novoExame);

    }

    public void excluir(int id) {
        Exame exame = repositorio.find(id);
        repositorio.delete(exame);
    }

}
