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
public class Consulta extends Procedimento {

    private Medico medico;
    private LocalDateTime dataRetorno;

    public Consulta(int id, Gestante paciente, String descricao, LocalDateTime data, double valor, String status,
            Relatorio relatorio, LocalDateTime deletadoEm, Medico medico, LocalDateTime dataRetorno) {
        super(id, paciente, descricao, dataRetorno, valor, status, relatorio, deletadoEm);
        this.medico = medico;
        this.dataRetorno = dataRetorno;
    }

}
