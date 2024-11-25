package com.cantarino.souza.model.enums;

public enum MetodoPagamento {
    DINHEIRO("Dinheiro"),
    CREDITO("Crédito"),
    DEBITO("Débito"),
    PIX("Pix");

    private final String value;

    MetodoPagamento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
