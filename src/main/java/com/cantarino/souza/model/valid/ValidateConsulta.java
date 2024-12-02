package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Procedimento;

public class ValidateConsulta extends ValidateProcedimento {

    public Consulta validaCamposEntrada(String descricao, String data, String duracao, String valor, String status,
            String deletadoEm) {

        Consulta consulta = new Consulta();

        Procedimento base = super.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm);
        consulta.setDescricao(base.getDescricao());
        consulta.setData(base.getData());
        consulta.setDuracao(base.getDuracao());
        consulta.setValor(base.getValor());
        consulta.setStatus(base.getStatus());
        consulta.setDeletadoEm(base.getDeletadoEm());

        return consulta;

    }

}
