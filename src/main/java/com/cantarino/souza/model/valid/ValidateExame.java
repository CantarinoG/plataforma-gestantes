package com.cantarino.souza.model.valid;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.exceptions.ExameException;
import com.cantarino.souza.model.exceptions.GestanteException;

public class ValidateExame extends ValidateProcedimento {

    public Exame validaCamposEntrada(String descricao, String data, String valor, String status,
            String deletadoEm, String dataResultado, String tipoAmostra) {

        Exame exame = new Exame();
        Procedimento base = super.validaCamposEntrada(descricao, data, valor, status, deletadoEm);
        exame.setDescricao(base.getDescricao());
        exame.setData(base.getData());
        exame.setValor(base.getValor());
        exame.setStatus(base.getStatus());
        exame.setDeletadoEm(base.getDeletadoEm());

        if (dataResultado == null || dataResultado.isEmpty())
            throw new GestanteException("ERRO: Campo previsão de resultado não pode ser vazio.");
        LocalDate date;
        try {
            date = LocalDate.parse(dataResultado);
        } catch (DateTimeParseException e) {
            throw new GestanteException("ERRO: Formato de data de resultado inválido.");
        }
        exame.setDataResultado(date);

        if (tipoAmostra == null || tipoAmostra.isEmpty())
            throw new ExameException("ERRO: Campo tipo de amostra não pode ser vazio.");
        exame.setTipoAmostra(tipoAmostra);

        return exame;

    }

}
