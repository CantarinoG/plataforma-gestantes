package com.cantarino.souza.view;

import com.cantarino.souza.controller.AdminController;
import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.controller.SecretarioController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;

public class AuthTemp {
    private static AuthTemp instance;
    private Usuario usuario;

    private AuthTemp() {
        AdminController admC = new AdminController();
        this.usuario = admC.buscarPorId(3);

        // MedicoController medicoC = new MedicoController();
        // this.usuario = medicoC.buscarPorId(13);

        // GestanteController gestanteC = new GestanteController();
        // this.usuario = gestanteC.buscarPorId(11);

        // SecretarioController secretarioC = new SecretarioController();
        // this.usuario = secretarioC.buscarPorId(46);
    }

    public static AuthTemp getInstance() {
        if (instance == null) {
            instance = new AuthTemp();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
