package com.cantarino.souza.model.entities;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Exame extends Procedimento {
    private LocalDateTime dataColeta;
    private LocalDateTime dataResultado;
    private String tipoAmostra;
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
