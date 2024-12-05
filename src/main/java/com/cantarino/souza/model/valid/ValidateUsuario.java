package com.cantarino.souza.model.valid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.UsuarioException;

public class ValidateUsuario {

    private boolean isCpfValido(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.length() == 11 && cpf.matches("[0-9]*")) {
            String[] vet = cpf.split("");

            int digito1 = Integer.parseInt(vet[9]);
            int digito2 = Integer.parseInt(vet[10]);

            int soma1 = 0;
            int fator1 = 10;
            for (int i = 0; i <= 8; i++) {
                int val = Integer.parseInt(vet[i]);
                soma1 = soma1 + (fator1 * val);
                fator1--;
            }

            int resultado1 = (soma1 * 10) % 11;
            if (resultado1 == 10)
                resultado1 = 0;

            if (resultado1 != digito1) {
                return false;
            }

            int soma2 = 0;
            int fator2 = 11;
            for (int i = 0; i <= 9; i++) {
                int val = Integer.parseInt(vet[i]);
                soma2 = soma2 + (fator2 * val);
                fator2--;
            }

            int resultado2 = (soma2 * 10) % 11;
            if (resultado2 == 10)
                resultado2 = 0;

            if (resultado2 != digito2) {
                return false;
            }

            for (int i = 0; i <= 9; i++) {
                int val = Integer.parseInt(vet[i]);
                int valProx = Integer.parseInt(vet[i + 1]);
                if (val != valProx)
                    return true;
            }
        }

        return false;
    }

    public String validaSenha(String senha) {
        if (senha == null || senha.isEmpty())
            throw new UsuarioException("ERRO: Campo senha não pode ser vazio.");
        if (senha.length() < 6)
            throw new UsuarioException("ERRO: Senha deve ter pelo menos 6 caracteres.");
        return senha;
    }

    public void validaExclusão(Usuario usuario, int idUsuarioAutenticado) {
        if (usuario.getId() == idUsuarioAutenticado) {
            throw new UsuarioException("ERRO: Você não pode deletar a si mesmo.");
        }
    }

    public Usuario validaCamposEntrada(String cpf, String nome, String email, String senha, String senhaConfirmada,
            String dataNascimento,
            String telefone, String endereco, String deletadoEm) {

        Usuario usuario = new Usuario();

        if (cpf == null || cpf.isEmpty())
            throw new UsuarioException("ERRO: Campo CPF não pode ser vazio.");
        if (!isCpfValido(cpf))
            throw new UsuarioException("ERRO: CPF não é válido");
        usuario.setCpf(cpf);

        if (nome == null || nome.isEmpty())
            throw new UsuarioException("ERRO: Campo nome não pode ser vazio");
        usuario.setNome(nome);

        if (email == null || email.isEmpty())
            throw new UsuarioException("ERRO: Campo email não pode ser vazio.");
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            throw new UsuarioException("ERRO: Email inválido.");
        usuario.setEmail(email);

        if (!senha.equals(senhaConfirmada)) {
            throw new UsuarioException("ERRO: As senhas não coincidem.");
        }

        String senhaValidada = validaSenha(senha);
        usuario.setSenha(senhaValidada);

        if (dataNascimento == null || dataNascimento.isEmpty())
            throw new UsuarioException("ERRO: Campo data de nascimento não pode ser vazio.");
        LocalDate dataConvertida;
        try {
            dataConvertida = LocalDate.parse(dataNascimento);
        } catch (DateTimeParseException e) {
            throw new UsuarioException("ERRO: Formato de data inválido.");
        }
        usuario.setDataNascimento(dataConvertida);

        if (telefone == null || telefone.isEmpty())
            throw new UsuarioException("ERRO: Campo telefone não pode ser vazio.");
        if (telefone.length() < 9 || telefone.length() > 13)
            throw new UsuarioException(
                    "ERRO: Telefone deve estar nos formatos (xxxxx-xxxx), ((xx) xxxxx-xxxx) ou (+xx (xx) xxxxx-xxxx).");
        usuario.setTelefone(telefone);

        if (endereco == null || endereco.isEmpty())
            throw new UsuarioException("ERRO: Campo endereço não pode ser vazio.");
        usuario.setEndereco(endereco);

        if (deletadoEm != null) {
            LocalDateTime dataExclusao;
            try {
                dataExclusao = LocalDateTime.parse(deletadoEm);
            } catch (DateTimeParseException e) {
                throw new UsuarioException("ERRO: Formato de data inválido.");
            }
            usuario.setDeletadoEm(dataExclusao);
        }

        return usuario;
    }
}