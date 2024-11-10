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
@DiscriminatorValue("GESTANTE")
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Gestante extends Usuario {

    private LocalDate previsaoParto;
    private String contatoEmergencia;
    private String historicoMedico;
    private String tipoSanguineo;

    public Gestante(int id, String cpf, String nome, String email, String senha, LocalDate dataNascimento,
            String telefone, String endereco, LocalDateTime deletadoEm, LocalDate previsaoParto,
            String contatoEmergencia,
            String historicoMedico, String tipoSanguineo) {
        super(id, cpf, nome, email, senha, dataNascimento, telefone, endereco, deletadoEm);
        this.previsaoParto = previsaoParto;
        this.contatoEmergencia = contatoEmergencia;
        this.historicoMedico = historicoMedico;
        this.tipoSanguineo = tipoSanguineo;
    }

}
