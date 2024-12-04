package com.cantarino.souza.model.valid;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.ExameException;

public class ValidateExame extends ValidateProcedimento {

    public Exame validaCamposEntrada(String descricao, String data, String duracao, String valor, String status,
            String deletadoEm, String dataResultado, String laboratorio, Gestante paciente, Usuario requisitadoPor) {

        Exame exame = new Exame();
        Procedimento base = super.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm);
        exame.setDescricao(base.getDescricao());
        exame.setData(base.getData());
        exame.setDuracao(base.getDuracao());
        exame.setValor(base.getValor());
        exame.setStatus(base.getStatus());
        exame.setDeletadoEm(base.getDeletadoEm());

        if (dataResultado != null) {
            if (dataResultado.isEmpty())
                throw new ExameException("ERRO: Campo previsão de resultado não pode ser vazio.");
            LocalDate dataConvertida;
            try {
                dataConvertida = LocalDate.parse(dataResultado);
                if (dataConvertida.isBefore(base.getData().toLocalDate())) {
                    throw new ExameException("ERRO: Data do resultado não pode ser anterior à data do exame.");
                }
            } catch (DateTimeParseException e) {
                throw new ExameException("ERRO: Formato de data de resultado inválido.");
            }
            exame.setDataResultado(dataConvertida);
        }

        if (laboratorio == null || laboratorio.isEmpty())
            throw new ExameException("ERRO: Campo laboratório não pode ser vazio.");
        exame.setLaboratorio(laboratorio);

        if (paciente == null) {
            throw new ExameException("ERRO: Paciente não pode ser nulo");
        }
        exame.setPaciente(paciente);
        exame.setRequisitadoPor(requisitadoPor);

        return exame;

    }

}