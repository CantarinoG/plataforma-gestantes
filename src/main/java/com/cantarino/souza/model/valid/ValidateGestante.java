package com.cantarino.souza.model.valid;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.GestanteException;

public class ValidateGestante extends ValidateUsuario {

    public Gestante validaCamposEntrada(String cpf, String nome, String email, String senha, String senhaConfirmada,
            String dataNascimento,
            String telefone, String endereco, String deletadoEm, String previsaoParto, String contatoEmergencia,
            String historicoMedico, String tipoSanguineo) {

        Gestante gestante = new Gestante();
        Usuario base = super.validaCamposEntrada(cpf, nome, email, senha, senhaConfirmada, dataNascimento, telefone,
                endereco,
                deletadoEm);
        gestante.setCpf(base.getCpf());
        gestante.setNome(base.getNome());
        gestante.setEmail(base.getEmail());
        gestante.setSenha(base.getSenha());
        gestante.setDataNascimento(base.getDataNascimento());
        gestante.setTelefone(base.getTelefone());
        gestante.setEndereco(base.getEndereco());
        gestante.setDeletadoEm(base.getDeletadoEm());

        if (previsaoParto != null) {
            if (previsaoParto.isEmpty())
                throw new GestanteException("ERRO: Campo previsão de parto não válido.");
            LocalDate dataConvertida;
            try {
                dataConvertida = LocalDate.parse(previsaoParto);
                if (dataConvertida.isBefore(LocalDate.now())) {
                    throw new GestanteException("ERRO: Data de previsão de parto não pode ser anterior a hoje.");
                }
            } catch (DateTimeParseException e) {
                throw new GestanteException("ERRO: Formato de data inválido.");
            }
            gestante.setPrevisaoParto(dataConvertida);
        }

        if (contatoEmergencia == null || contatoEmergencia.isEmpty())
            throw new GestanteException("ERRO: Campo contato de emergência não pode ser vazio.");
        gestante.setContatoEmergencia(contatoEmergencia);

        if (tipoSanguineo == null || tipoSanguineo.isEmpty())
            throw new GestanteException("ERRO: Campo tipo sanguíneo não pode ser vazio.");
        gestante.setTipoSanguineo(tipoSanguineo);

        return gestante;
    }

}