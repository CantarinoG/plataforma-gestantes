package com.cantarino.souza.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String telefone;
    private String endereco;
    private LocalDateTime deletadoEm;
}
