package com.cantarino.souza.model.valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.exceptions.ProcedimentoException;

public class ValidateConsulta extends ValidateProcedimento {

    public Consulta validaCamposEntrada(String descricao, String data, String valor, String status,
            String deletadoEm, String dataRetorno) {

        Consulta consulta = new Consulta();

        Procedimento base = super.validaCamposEntrada(descricao, data, valor, status, deletadoEm);
        consulta.setDescricao(base.getDescricao());
        consulta.setData(base.getData());
        consulta.setValor(base.getValor());
        consulta.setStatus(base.getStatus());
        consulta.setDeletadoEm(base.getDeletadoEm());

        if (dataRetorno == null || dataRetorno.isEmpty())
            throw new ProcedimentoException("ERRO: Campo data de retorno não pode ser vazio.");
        LocalDateTime validDate;
        try {
            validDate = LocalDateTime.parse(dataRetorno);
        } catch (DateTimeParseException e) {
            throw new ProcedimentoException("ERRO: Formato de data inválido.");
        }
        consulta.setDataRetorno(validDate);

        return consulta;

    }

}
