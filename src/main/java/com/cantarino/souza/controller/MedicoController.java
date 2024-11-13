package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.MedicoDao;

public class MedicoController {

    private MedicoDao repositorio;

    public MedicoController() {
        repositorio = new MedicoDao();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMMedico(repositorio.findAll()), null);
    }

}
