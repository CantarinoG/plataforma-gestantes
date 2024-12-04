package com.cantarino.souza.model.enums;

public enum MetodoPagamento {
    DINHEIRO("Dinheiro"),
    CREDITO("Crédito"),
    DEBITO("Débito"),
    PIX("Pix");

    private final String valor;

    MetodoPagamento(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
