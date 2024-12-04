package com.cantarino.souza.model.enums;

public enum TipoUsuario {
    GESTANTE("Gestante"),
    MEDICO("Médico(a)"),
    SECRETARIO("Secretário(a)"),
    ADMIN("Administrador(a)");

    private final String valor;

    TipoUsuario(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}