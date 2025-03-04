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

    public Gestante(String cpf, String nome, String email, String senha, String codigoRecuperacao,
            LocalDateTime validadeCodigoRecuperacao, LocalDate dataNascimento,
            String telefone, String endereco, LocalDateTime deletadoEm, LocalDate previsaoParto,
            String contatoEmergencia,
            String historicoMedico, String tipoSanguineo) {
        super(0, cpf, nome, email, senha, codigoRecuperacao, validadeCodigoRecuperacao, dataNascimento, telefone,
                endereco, deletadoEm);
        this.previsaoParto = previsaoParto;
        this.contatoEmergencia = contatoEmergencia;
        this.historicoMedico = historicoMedico;
        this.tipoSanguineo = tipoSanguineo;
    }

}
