package com.cantarino.souza.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("EXAME")
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Exame extends Procedimento {
    private LocalDate dataResultado;
    private String laboratorio;

    @ManyToOne
    @JoinColumn(name = "requisitado_por_id")
    private Usuario requisitadoPor;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    public Exame(Gestante paciente, String descricao, LocalDateTime data, int duracao, double valor, String status,
            Relatorio relatorio, LocalDateTime deletadoEm, LocalDate dataResultado, String laboratorio,
            Usuario requisitadoPor, Medico medico) {
        super(0, paciente, descricao, data, duracao, valor, status, relatorio, deletadoEm);
        this.laboratorio = laboratorio;
        this.dataResultado = dataResultado;
        this.requisitadoPor = requisitadoPor;
        this.medico = medico;
    }

}
