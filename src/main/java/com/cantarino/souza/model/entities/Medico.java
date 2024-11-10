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
public class Medico extends Usuario {

    private String especializacao;
    private String crm;

    public Medico(int id, String cpf, String nome, String email, String senha, LocalDate dataNascimento,
            String telefone, String endereco, LocalDateTime deletadoEm, String especializacao, String crm) {
        super(id, cpf, nome, email, senha, dataNascimento, telefone, endereco, deletadoEm);
        this.especializacao = especializacao;
        this.crm = crm;
    }

}
