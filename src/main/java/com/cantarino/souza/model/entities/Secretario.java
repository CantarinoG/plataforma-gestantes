package com.cantarino.souza.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Secretario extends Usuario {

    private LocalDate dataContratacao;

    public Secretario(int id, String cpf, String nome, String email, String senha, LocalDate dataNascimento,
            String telefone, String endereco, LocalDateTime deletadoEm, LocalDate dataContratacao) {
        super(id, cpf, nome, email, senha, dataNascimento, telefone, endereco, deletadoEm);
        this.dataContratacao = dataContratacao;
    }

}
