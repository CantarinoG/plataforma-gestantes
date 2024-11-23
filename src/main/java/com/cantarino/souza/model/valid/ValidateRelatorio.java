package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.exceptions.RelatorioException;

public class ValidateRelatorio {
    public Relatorio validaCamposEntrada( String resultado, String observacoes){
        Relatorio relatorio = new Relatorio();
        if(resultado == null || resultado.isEmpty()){
            throw new RelatorioException("ERRO: Campo resultado não pode ser vazio.");
        }
        relatorio.setResultado(resultado);
        if(observacoes == null || observacoes.isEmpty()){
            throw new RelatorioException("ERRO: Campo observações não pode ser vazio.");
        }
        relatorio.setObeservacoes(observacoes);
        return relatorio;
    }
}
