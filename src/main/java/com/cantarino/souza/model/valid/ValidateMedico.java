package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.MedicoException;

public class ValidateMedico extends ValidateUsuario {

    public Medico validaCamposEntrada(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String especializacao, String crm) {

        Medico medico = new Medico();
        Usuario base = super.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm);
        medico.setCpf(base.getCpf());
        medico.setNome(base.getNome());
        medico.setEmail(base.getEmail());
        medico.setSenha(base.getSenha());
        medico.setDataNascimento(base.getDataNascimento());
        medico.setTelefone(base.getTelefone());
        medico.setEndereco(base.getEndereco());
        medico.setDeletadoEm(base.getDeletadoEm());

        if (especializacao == null || especializacao.isEmpty())
            throw new MedicoException("ERRO: Campo especialização não pode ser vazio.");
        medico.setEspecializacao(especializacao);

        if (crm == null || crm.isEmpty())
            throw new MedicoException("ERRO: Campo CRM não pode ser vazio.");
        if (!crm.matches("^\\d+\\s?[A-Z]{2}$"))
            throw new MedicoException("ERRO: CRM no formato inválido.");
        medico.setCrm(crm);

        return medico;

    }

}
