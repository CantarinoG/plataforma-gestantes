package com.cantarino.souza.controller;

import java.time.LocalDateTime;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMSecretario;
import com.cantarino.souza.model.dao.SecretarioDao;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.exceptions.GestanteException;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateSecretario;

public class SecretarioController {

    private SecretarioDao repositorio;
    private ValidateSecretario validator;
    private NotificadorEmail notificador;

    public SecretarioController() {
        repositorio = new SecretarioDao();
        validator = new ValidateSecretario();
        notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMSecretario(repositorio.findAll()), null);
    }

    public void cadastrar(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String dataContratacao) {

        Secretario novoSecretario = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone,
                endereco, deletadoEm, dataContratacao);
        String hashSenha = Util.hashPassword(novoSecretario.getSenha());
        novoSecretario.setSenha(hashSenha);

        Secretario existingSecretario = repositorio.findByCpf(novoSecretario.getCpf());
        if (existingSecretario != null) {
            throw new GestanteException("Já existe um secretário cadastrado com esse cpf.");
        }
        existingSecretario = repositorio.findByEmail(novoSecretario.getEmail());
        if (existingSecretario != null) {
            throw new GestanteException("Já existe um secretário cadastrada com esse email.");
        }

        repositorio.save(novoSecretario);

    }

    public Secretario buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void atualizar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String dataContratacao) {
        Secretario novoSecretario = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone,
                endereco, deletadoEm, dataContratacao);
        novoSecretario.setId(id);

        Secretario existingSecretario = repositorio.findByCpf(novoSecretario.getCpf());
        if (existingSecretario != null && existingSecretario.getId() != id) {
            throw new GestanteException("Já existe um secretário cadastrado com esse cpf.");
        }
        existingSecretario = repositorio.findByEmail(novoSecretario.getEmail());
        if (existingSecretario != null && existingSecretario.getId() != id) {
            throw new GestanteException("Já existe um secretário cadastrada com esse email.");
        }

        repositorio.update(novoSecretario);
    }

    public void excluir(int id) {
        Secretario secretario = repositorio.find(id);
        repositorio.delete(secretario);
    }

    public void atualizaSenha(Secretario usuario, String senha) {
        String senhaValidada = validator.validaSenha(senha);
        String hashSenha = Util.hashPassword(senhaValidada);
        usuario.setSenha(hashSenha);
        repositorio.update(usuario);
    }

    public Secretario adicionarCodigoRecuperacao(String cpf, String codigo) {
        Secretario secretario = repositorio.findByCpf(cpf);
        if (secretario != null) {
            secretario.setCodigoRecuperacao(codigo);
            secretario.setValidadeCodigoRecuperacao(LocalDateTime.now().plusMinutes(30));
            repositorio.update(secretario);
            notificador.notificar(secretario, "BemGestar | Recuperação de Senha", "Seu código de recuperação é: "
                    + codigo
                    + ". Pelos próximos 30 minutos, você vai conseguir logar na sua conta utilizando este código no lugar da senha. Entre na sua conta e seleciona a opção de mudar senha para redefinir sua senha.");
        }
        return secretario;
    }

}
