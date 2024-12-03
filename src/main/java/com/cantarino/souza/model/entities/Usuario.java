package com.cantarino.souza.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;
    protected String cpf;
    protected String nome;
    protected String email;
    protected String senha;
    protected String codigoRecuperacao;
    protected LocalDateTime validadeCodigoRecuperacao;
    protected LocalDate dataNascimento;
    protected String telefone;
    protected String endereco;
    protected LocalDateTime deletadoEm;
}
