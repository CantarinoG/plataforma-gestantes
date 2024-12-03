package com.cantarino.souza.model.valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.exceptions.ProcedimentoException;
import com.cantarino.souza.model.exceptions.UsuarioException;

public class ValidateProcedimento {

    public Procedimento validaCamposEntrada(String descricao, String data, String duracao, String valor, String status,
            String deletadoEm) {

        Procedimento procedimento = new Procedimento();

        if (descricao == null || descricao.isEmpty())
            throw new ProcedimentoException("ERRO: Campo descrição não pode ser vazio.");
        procedimento.setDescricao(descricao);

        if (data == null || data.isEmpty())
            throw new ProcedimentoException("ERRO: Campo data não pode ser vazio.");
        LocalDateTime validDate;
        try {
            validDate = LocalDateTime.parse(data);
            if (validDate.isBefore(LocalDateTime.now())) {
                throw new ProcedimentoException("ERRO: Data não pode ser anterior ao momento atual.");
            }
        } catch (DateTimeParseException e) {
            throw new ProcedimentoException("ERRO: Formato de data inválido.");
        }
        procedimento.setData(validDate);

        if (duracao == null || duracao.isEmpty())
            throw new ProcedimentoException("ERRO: Campo duração não pode ser vazio.");
        int validDuracao;
        try {
            validDuracao = Integer.parseInt(duracao.trim());
            if (validDuracao <= 0) {
                throw new ProcedimentoException("ERRO: Duração deve ser maior que zero.");
            }
        } catch (NumberFormatException e) {
            throw new ProcedimentoException("ERRO: Campo duração deve conter um número válido.");
        }
        procedimento.setDuracao(validDuracao);

        if (valor == null || valor.isEmpty())
            throw new ProcedimentoException("ERRO: Campo valor não pode ser vazio.");
        double validValor;
        try {
            valor = valor.replace(",", ".");
            validValor = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new ProcedimentoException("ERRO: Campo valor deve conter um número válido.");
        }
        if (validValor < 0) {
            throw new ProcedimentoException("ERRO: Valor não pode ser negativo.");
        }
        procedimento.setValor(validValor);

        if (status == null || status.isEmpty())
            throw new ProcedimentoException("ERRO: Campo status não pode ser vazio.");
        procedimento.setStatus(status);

        if (deletadoEm != null) {
            LocalDateTime deleteDate;
            try {
                deleteDate = LocalDateTime.parse(deletadoEm);
            } catch (DateTimeParseException e) {
                throw new UsuarioException("ERRO: Formato de data de delete inválido.");
            }
            procedimento.setDeletadoEm(deleteDate);
        }

        return procedimento;
    }

}
