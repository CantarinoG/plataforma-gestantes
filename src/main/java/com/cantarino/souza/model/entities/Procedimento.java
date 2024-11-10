package com.cantarino.souza.model.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Procedimento {
    private int id;
    private Gestante paciente;
    private String descricao;
    private LocalDateTime data;
    private double valor;
    private String status;
    private Relatorio relatorio;
    private LocalDateTime deletadoEm;
}