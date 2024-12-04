package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Usuario;

public class ValidateAdmin extends ValidateUsuario {

    public Admin validaCamposEntrada(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm) {

        Admin admin = new Admin();

        Usuario base = super.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm);
        admin.setCpf(base.getCpf());
        admin.setNome(base.getNome());
        admin.setEmail(base.getEmail());
        admin.setSenha(base.getSenha());
        admin.setDataNascimento(base.getDataNascimento());
        admin.setTelefone(base.getTelefone());
        admin.setEndereco(base.getEndereco());
        admin.setDeletadoEm(base.getDeletadoEm());

        return admin;

    }

}