package com.cantarino.souza.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("MEDICO")
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Medico extends Usuario {

    private String especializacao;
    private String crm;

    public Medico(String cpf, String nome, String email, String senha, String codigoRecuperacao,
            LocalDateTime validadeCodigoRecuperacao, LocalDate dataNascimento,
            String telefone, String endereco, LocalDateTime deletadoEm, String especializacao, String crm) {
        super(0, cpf, nome, email, senha, codigoRecuperacao, validadeCodigoRecuperacao, dataNascimento, telefone,
                endereco, deletadoEm);
        this.especializacao = especializacao;
        this.crm = crm;
    }

}
