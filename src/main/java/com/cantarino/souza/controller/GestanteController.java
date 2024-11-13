package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.GestanteDao;

public class GestanteController {

    private GestanteDao repositorio;

    public GestanteController() {
        repositorio = new GestanteDao();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMGestantes(repositorio.findAll()), null);
    }

}
