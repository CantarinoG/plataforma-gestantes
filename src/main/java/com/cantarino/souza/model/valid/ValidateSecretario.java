package com.cantarino.souza.model.valid;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.SecretarioException;

public class ValidateSecretario extends ValidateUsuario {

    public Secretario validaCamposEntrada(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String dataContratacao) {

        Secretario secretario = new Secretario();
        Usuario base = super.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm);
        secretario.setCpf(base.getCpf());
        secretario.setNome(base.getNome());
        secretario.setEmail(base.getEmail());
        secretario.setSenha(base.getSenha());
        secretario.setDataNascimento(base.getDataNascimento());
        secretario.setTelefone(base.getTelefone());
        secretario.setEndereco(base.getEndereco());
        secretario.setDeletadoEm(base.getDeletadoEm());

        if (dataContratacao == null || dataContratacao.isEmpty())
            throw new SecretarioException("ERRO: Campo data de contratação não pode ser vazio.");
        LocalDate dataConvertida;
        try {
            dataConvertida = LocalDate.parse(dataContratacao);
            if (dataConvertida.isAfter(LocalDate.now())) {
                throw new SecretarioException("ERRO: Data de contratação não pode ser no futuro.");
            }
        } catch (DateTimeParseException e) {
            throw new SecretarioException("ERRO: Formato de data inválido.");
        }
        secretario.setDataContratacao(dataConvertida);

        return secretario;

    }

}