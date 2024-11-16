package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.ConsultaDao;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.valid.ValidateConsulta;

public class ConsultaController {

    private ConsultaDao repositorio;
    private ValidateConsulta validator;

    public ConsultaController() {
        repositorio = new ConsultaDao();
        validator = new ValidateConsulta();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMConsulta(repositorio.findAll()), null);
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

    public void excluir(int id) {
        Consulta consulta = repositorio.find(id);
        repositorio.delete(consulta);
    }

    public Consulta buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void atualizarTabelaPorMedicoId(JTable grd, int id) {
        Util.jTableShow(grd, new TMConsulta(repositorio.findAllWithMedicoId(id)), null);
    }

    public void atualizarTabelaPorMedicoIdStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMConsulta(repositorio.findAllWithMedicoIdStatus(id, status)), null);
    }

    public void atualizarTabelaPorGestanteId(JTable grd, int id) {
        Util.jTableShow(grd, new TMConsulta(repositorio.findAllWithGestanteId(id)), null);
    }

    public void atualizarTabelaPorGestanteIdStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMConsulta(repositorio.findAllWithGestanteIdStatus(id, status)), null);
    }

}
