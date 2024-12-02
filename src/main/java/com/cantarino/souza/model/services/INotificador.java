package com.cantarino.souza.model.services;

import com.cantarino.souza.model.entities.Usuario;

public interface INotificador {
    boolean notificar(Usuario usuario, String titulo, String mensagem);
}
