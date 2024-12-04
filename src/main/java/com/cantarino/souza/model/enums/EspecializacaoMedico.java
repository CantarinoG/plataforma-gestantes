package com.cantarino.souza.model.enums;

public enum EspecializacaoMedico {
    OBSTETRA("Obstetricía"),
    GINECOLOGISTA("Ginecologia"),
    ULTRASSONOGRAFISTA("Ultrassonografia"),
    ENDOCRINOLOGISTA("Endocrinologia"),
    NUTRICIONISTA("Nutrição"),
    MEDICO_FETAL("Medicina Fetal"),
    CLINICO_GERAL("Clínico Geral"),
    OUTRO("Outro");

    private final String valor;

    EspecializacaoMedico(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
