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

    public void excluir(int id) {
        Consulta consulta = repositorio.find(id);
        repositorio.delete(consulta);
    }

}
