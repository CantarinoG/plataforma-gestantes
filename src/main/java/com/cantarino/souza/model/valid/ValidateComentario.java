package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Comentario;
import com.cantarino.souza.model.exceptions.ComentarioException;

public class ValidateComentario {
    public Comentario validaCamposEntrada(String conteudo){
        Comentario comentario = new Comentario();        
         if (conteudo == null || conteudo.isEmpty())
            throw new ComentarioException("ERRO: Campo de conteudo não pode ser vazio.");

        comentario.setConteudo(conteudo);
        return comentario;
    }
}
