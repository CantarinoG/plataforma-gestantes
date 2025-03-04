package com.cantarino.souza.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SECRETARIO")
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Secretario extends Usuario {

    private LocalDate dataContratacao;

    public Secretario(String cpf, String nome, String email, String senha, String codigoRecuperacao,
            LocalDateTime validadeCodigoRecuperacao, LocalDate dataNascimento,
            String telefone, String endereco, LocalDateTime deletadoEm, LocalDate dataContratacao) {
        super(0, cpf, nome, email, senha, codigoRecuperacao, validadeCodigoRecuperacao, dataNascimento, telefone,
                endereco, deletadoEm);
        this.dataContratacao = dataContratacao;
    }

}
