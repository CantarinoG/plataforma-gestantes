package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.ExameDao;

public class ExameController {

    private ExameDao repositorio;

    public ExameController() {
        repositorio = new ExameDao();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMExame(repositorio.findAll()), null);
    }

}
