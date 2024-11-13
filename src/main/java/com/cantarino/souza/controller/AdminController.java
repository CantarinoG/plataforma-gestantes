package com.cantarino.souza.controller;

import javax.swing.JTable;

import com.cantarino.souza.model.dao.AdminDao;

public class AdminController {

    private AdminDao repositorio;

    public AdminController() {
        repositorio = new AdminDao();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMAdmin(repositorio.findAll()), null);
    }

}
