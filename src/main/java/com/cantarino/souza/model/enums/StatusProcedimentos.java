package com.cantarino.souza.model.enums;

public enum StatusProcedimentos {
    AGENDADA("Agendado(a)"),
    CANCELADA("Cancelado(a)"),
    CONCLUIDA("Concluído(a)");

    private final String valor;

    StatusProcedimentos(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
