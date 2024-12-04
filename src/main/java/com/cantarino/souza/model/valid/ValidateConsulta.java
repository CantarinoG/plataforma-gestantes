package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.exceptions.ConsultaException;

public class ValidateConsulta extends ValidateProcedimento {

    public Consulta validaCamposEntrada(String descricao, String data, String duracao, String valor, String status,
            String deletadoEm, Gestante paciente, Medico medico, Consulta retorno) {

        Consulta consulta = new Consulta();

        Procedimento base = super.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm);
        consulta.setDescricao(base.getDescricao());
        consulta.setData(base.getData());
        consulta.setDuracao(base.getDuracao());
        consulta.setValor(base.getValor());
        consulta.setStatus(base.getStatus());
        consulta.setDeletadoEm(base.getDeletadoEm());

        if (paciente == null) {
            throw new ConsultaException("ERRO: Paciente não pode ser nulo");
        }
        if (medico == null) {
            throw new ConsultaException("ERRO: Médico não pode ser nulo");
        }

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setRetorno(retorno);

        return consulta;

    }

}