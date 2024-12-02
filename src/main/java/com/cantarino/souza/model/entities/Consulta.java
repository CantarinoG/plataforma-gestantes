package com.cantarino.souza.model.entities;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("CONSULTA")
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Consulta extends Procedimento {

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "retorno_id")
    private Consulta retorno;

    public Consulta(Gestante paciente, String descricao, LocalDateTime data, int duracao, double valor, String status,
            Relatorio relatorio, LocalDateTime deletadoEm, Medico medico, Consulta retorno) {
        super(0, paciente, descricao, data, duracao, valor, status, relatorio, deletadoEm);
        this.medico = medico;
        this.retorno = retorno;
    }

}
