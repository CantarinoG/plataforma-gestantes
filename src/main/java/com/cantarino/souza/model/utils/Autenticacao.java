package com.cantarino.souza.model.utils;

import com.cantarino.souza.model.entities.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Autenticacao {

    private static Autenticacao instance;
    private Usuario usuario;

    private Autenticacao() {
    }

    public static Autenticacao getInstance() {
        if (instance == null) {
            instance = new Autenticacao();
        }
        return instance;
    }

}
