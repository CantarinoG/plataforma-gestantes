package com.cantarino.souza.model.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Procedimento {
    protected int id;
    protected Gestante paciente;
    protected String descricao;
    protected LocalDateTime data;
    protected double valor;
    protected String status;
    protected Relatorio relatorio;
    protected LocalDateTime deletadoEm;
}