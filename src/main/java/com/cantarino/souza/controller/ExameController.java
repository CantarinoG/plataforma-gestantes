package com.cantarino.souza.controller;

import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMExame;
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
        Util.jTableShow(grd, new TMExame(repositorio.findAll()), TMExame.getCustomRenderer());
    }

    public void cadastrar(Gestante paciente, String descricao, String data, String valor, String status,
            Relatorio relatorio, String deletadoEm, String dataResultado, Usuario requisitadoPor, String laboratorio) {

        Exame novoExame = validator.validaCamposEntrada(descricao, data, valor, status, deletadoEm, dataResultado,
                laboratorio);
        novoExame.setPaciente(paciente);
        novoExame.setRequisitadoPor(requisitadoPor);
        repositorio.save(novoExame);

    }

    public Exame buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void atualizar(int id, Gestante paciente, String descricao, String data, String valor, String status,
            Relatorio relatorio, String deletadoEm, String dataResultado, Usuario requisitadoPor, String laboratorio) {
        Exame novoExame = validator.validaCamposEntrada(descricao, data, valor, status, deletadoEm, dataResultado,
                laboratorio);
        novoExame.setPaciente(paciente);
        novoExame.setRequisitadoPor(requisitadoPor);
        novoExame.setId(id);
        repositorio.update(novoExame);
    }

    public List<Exame> buscarTodas() {
        return repositorio.findAll();
    }

    public void excluir(int id) {
        Exame exame = repositorio.find(id);
        repositorio.delete(exame);
    }

    public void filtrarTabelaPorIdGestante(JTable grd, int id) {
        Util.jTableShow(grd, new TMExame(repositorio.filterGestanteId(id)), TMExame.getCustomRenderer());
    }

    public void filtrarTabelaPorIdGestanteStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMExame(repositorio.filterGestanteIdStatus(id, status)), TMExame.getCustomRenderer());
    }

}
