package com.cantarino.souza.model.valid;

import java.time.LocalDateTime;

import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.PublicacaoException;

public class ValidatePublicacao {
    public Publicacao validaCamposEntrada(String titulo, String corpo, Usuario autor, LocalDateTime data,
            boolean isAnonimo) {
        Publicacao publicacao = new Publicacao();
        if (titulo == null || titulo.isEmpty())
            throw new PublicacaoException("ERRO: Campo titulo não pode ser vazio.");
        publicacao.setTitulo(titulo);

        if (corpo == null || corpo.isEmpty())
            throw new PublicacaoException("ERRO: Campo corpo não pode ser vazio.");
        publicacao.setCorpo(corpo);

        if (autor == null) {
            throw new PublicacaoException("ERRO: Autor não pode ser nulo");
        }
        publicacao.setAutor(autor);

        if (data == null) {
            throw new PublicacaoException("ERRO: Data não pode ser nula");
        }
        publicacao.setData(data);

        publicacao.setAnonimo(isAnonimo);

        return publicacao;
    }
}
