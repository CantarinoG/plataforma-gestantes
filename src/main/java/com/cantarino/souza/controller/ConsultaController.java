package com.cantarino.souza.controller;

import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMConsulta;
import com.cantarino.souza.model.dao.ConsultaDao;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.model.valid.ValidateConsulta;

public class ConsultaController {

    private ConsultaDao repositorio;
    private ValidateConsulta validator;

    public ConsultaController() {
        repositorio = new ConsultaDao();
        validator = new ValidateConsulta();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMConsulta(repositorio.findAll()), TMConsulta.getCustomRenderer());
    }

    public void cadastrar(Gestante paciente, String descricao, String data, String valor, String status,
            Relatorio relatorio, String deletadoEm, Medico medico, String dataRetorno) {

        Consulta novaConsulta = validator.validaCamposEntrada(descricao, data, valor, status, deletadoEm, dataRetorno);
        novaConsulta.setPaciente(paciente);
        novaConsulta.setMedico(medico);
        repositorio.save(novaConsulta);

    }

    public void atualizar(int id, Gestante paciente, String descricao, String data, String valor, String status,
            Relatorio relatorio, String deletadoEm, Medico medico, String dataRetorno) {
        Consulta novaConsulta = validator.validaCamposEntrada(descricao, data, valor, status, deletadoEm, dataRetorno);
        novaConsulta.setPaciente(paciente);
        novaConsulta.setMedico(medico);
        novaConsulta.setId(id);
        repositorio.update(novaConsulta);
    }

    public List<Consulta> buscarTodas() {
        return repositorio.findAll();
    }

    public void excluir(int id) {
        Consulta consulta = repositorio.find(id);
        repositorio.delete(consulta);
    }

    public Consulta buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void filtrarTabelaPorIdMedico(JTable grd, int id) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterMedicoId(id)), TMConsulta.getCustomRenderer());
    }

    public void filtrarTabelaPorIdMedicoStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterMedicoIdStatus(id, status)),
                TMConsulta.getCustomRenderer());
    }

    public void filtrarTabelaPorIdGestante(JTable grd, int id) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterGestanteId(id)), TMConsulta.getCustomRenderer());
    }

    public void filtrarTabelaPorIdGestanteStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterGestanteIdStatus(id, status)),
                TMConsulta.getCustomRenderer());
    }

    public void cancelar(int id) {
        Consulta consulta = repositorio.find(id);
        consulta.setStatus(StatusProcedimentos.CANCELADA.getValue());
        repositorio.update(consulta);
    }

}
