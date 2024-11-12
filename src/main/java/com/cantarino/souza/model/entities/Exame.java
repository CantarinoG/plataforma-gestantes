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
    private String tipoAmostra;

    @ManyToOne
    @JoinColumn(name = "requisitado_por_id")
    private Usuario requisitadoPor;

    public Exame(Gestante paciente, String descricao, LocalDateTime data, double valor, String status,
            Relatorio relatorio, LocalDateTime deletadoEm, LocalDate dataResultado,
            String tipoAmostra, Usuario requisitadoPor) {
        super(0, paciente, descricao, data, valor, status, relatorio, deletadoEm);
        this.dataResultado = dataResultado;
        this.tipoAmostra = tipoAmostra;
        this.requisitadoPor = requisitadoPor;
    }

}
