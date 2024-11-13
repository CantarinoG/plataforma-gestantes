package com.cantarino.souza.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Admin extends Usuario {

    public Admin(String cpf, String nome, String email, String senha, LocalDate dataNascimento,
            String telefone, String endereco, LocalDateTime deletadoEm) {
        super(0, cpf, nome, email, senha, dataNascimento, telefone, endereco, deletadoEm);
    }

}
