package com.cantarino.souza.model.enums;

public enum StatusProcedimentos {
    AGENDADA("Agendado(a)"),
    CANCELADA("Cancelado(a)"),
    CONCLUIDA("Concluído(a)");

    private final String value;

    StatusProcedimentos(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
