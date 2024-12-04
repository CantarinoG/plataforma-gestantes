package com.cantarino.souza.model.valid;

import java.time.LocalDateTime;

import com.cantarino.souza.model.entities.Comentario;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.ComentarioException;

public class ValidateComentario {
    public Comentario validaCamposEntrada(String conteudo, LocalDateTime data, boolean isAnonimo, Usuario autor,
            Publicacao publicacao) {
        Comentario comentario = new Comentario();
        if (conteudo == null || conteudo.isEmpty())
            throw new ComentarioException("ERRO: Campo de conteudo não pode ser vazio.");

        if (data == null) {
            throw new ComentarioException("ERRO: Data não pode ser nula");
        }

        if (publicacao == null) {
            throw new ComentarioException("ERRO: Publicação não pode ser nula");
        }

        if (autor == null) {
            throw new ComentarioException("ERRO: Autor não pode ser nulo");
        }

        comentario.setConteudo(conteudo);
        comentario.setData(data);
        comentario.setPublicacao(publicacao);
        comentario.setAutor(autor);
        comentario.setAnonimo(isAnonimo);
        return comentario;
    }
}
