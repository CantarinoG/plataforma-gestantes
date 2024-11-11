package com.cantarino.souza.model.entities;

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
    private LocalDateTime dataColeta;
    private LocalDateTime dataResultado;
    private String tipoAmostra;

    @ManyToOne
    @JoinColumn(name = "requisitado_por_id")
    private Usuario requisitadoPor;

    public Exame(int id, Gestante paciente, String descricao, LocalDateTime data, double valor, String status,
            Relatorio relatorio, LocalDateTime deletadoEm, LocalDateTime dataColeta, LocalDateTime dataResultado,
            String tipoAmostra, Usuario requisitadoPor) {
        super(id, paciente, descricao, dataResultado, valor, status, relatorio, deletadoEm);
        this.dataColeta = dataColeta;
        this.dataResultado = dataResultado;
        this.tipoAmostra = tipoAmostra;
        this.requisitadoPor = requisitadoPor;
    }

}
