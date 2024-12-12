package com.cantarino.souza.model.utils;

import org.mindrot.jbcrypt.BCrypt;

public class GerenciadorCriptografia {

    public String criptografarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public boolean compararSenha(String senha, String senhaHash) {
        return BCrypt.checkpw(senha, senhaHash);
    }

}
