package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.ConsultaDao;

public class ConsultaController {

    private ConsultaDao repositorio;

    public ConsultaController() {
        repositorio = new ConsultaDao();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMConsulta(repositorio.findAll()), null);
    }

}
