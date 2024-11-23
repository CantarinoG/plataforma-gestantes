package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.exceptions.PublicacaoException;

public class ValidatePublicacao {
    public Publicacao validaCamposEntrada(String titulo,String corpo, String isMedico){
        Publicacao publicacao = new Publicacao();
        if (titulo == null || titulo.isEmpty())
            throw new PublicacaoException("ERRO: Campo titulo não pode ser vazio.");
        publicacao.setTitulo(titulo);

        if (corpo == null || corpo.isEmpty())
            throw new PublicacaoException("ERRO: Campo corpo não pode ser vazio.");
        publicacao.setCorpo(corpo);

        if (isMedico == null || isMedico.isEmpty())
            throw new PublicacaoException("ERRO: Campo isMedico não pode ser vazio.");
        publicacao.setMedico(Boolean.parseBoolean(isMedico));

        return publicacao;
    }
}
