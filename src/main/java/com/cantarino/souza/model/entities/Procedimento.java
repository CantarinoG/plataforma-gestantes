package com.cantarino.souza.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    protected Gestante paciente;
    protected String descricao;
    protected LocalDateTime data;
    protected double valor;
    protected String status;
    @ManyToOne
    @JoinColumn(name = "relatorio_id")
    protected Relatorio relatorio;
    protected LocalDateTime deletadoEm;
}