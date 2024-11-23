package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.exceptions.PublicacaoException;

public class ValidatePublicacao {
    public Publicacao validaCamposEntrada(String titulo, String corpo) {
        Publicacao publicacao = new Publicacao();
        if (titulo == null || titulo.isEmpty())
            throw new PublicacaoException("ERRO: Campo titulo não pode ser vazio.");
        publicacao.setTitulo(titulo);

        if (corpo == null || corpo.isEmpty())
            throw new PublicacaoException("ERRO: Campo corpo não pode ser vazio.");
        publicacao.setCorpo(corpo);

        return publicacao;
    }
}
