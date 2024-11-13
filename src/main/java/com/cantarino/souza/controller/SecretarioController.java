package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.SecretarioDao;

public class SecretarioController {

    private SecretarioDao repositorio;

    public SecretarioController() {
        repositorio = new SecretarioDao();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMSecretario(repositorio.findAll()), null);
    }

}
