package com.cantarino.souza.controller;

import java.time.LocalDateTime;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMSecretario;
import com.cantarino.souza.model.dao.SecretarioDao;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.exceptions.GestanteException;
import com.cantarino.souza.model.utils.GerenciadorCriptografia;
import com.cantarino.souza.model.utils.INotificador;
import com.cantarino.souza.model.utils.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateSecretario;

public class SecretarioController {

    private SecretarioDao repositorio;
    private ValidateSecretario validador;
    private INotificador notificador;
    private GerenciadorCriptografia gerenciadorCriptografia;

    public SecretarioController() {
        repositorio = new SecretarioDao();
        validador = new ValidateSecretario();
        notificador = new NotificadorEmail();
        gerenciadorCriptografia = new GerenciadorCriptografia();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMSecretario(repositorio.buscarTodos()), null);
    }

    public void salvar(String cpf, String nome, String email, String senha, String senhaConfirmada,
            String dataNascimento,
            String telefone, String endereco, String deletadoEm, String dataContratacao) {

        Secretario novoSecretario = validador.validaCamposEntrada(cpf, nome, email, senha, senhaConfirmada,
                dataNascimento, telefone,
                endereco, deletadoEm, dataContratacao);
        String hashSenha = gerenciadorCriptografia.criptografarSenha(novoSecretario.getSenha());
        novoSecretario.setSenha(hashSenha);

        Secretario existingSecretario = repositorio.buscarPorCpf(novoSecretario.getCpf());
        if (existingSecretario != null) {
            throw new GestanteException("ERRO: Já existe um secretário cadastrado com esse cpf.");
        }
        existingSecretario = repositorio.buscarPorEmail(novoSecretario.getEmail());
        if (existingSecretario != null) {
            throw new GestanteException("ERRO: Já existe um secretário cadastrada com esse email.");
        }

        repositorio.salvar(novoSecretario);

    }

    public Secretario buscar(int id) {
        return repositorio.buscar(id);
    }

    public void editar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String dataContratacao) {
        Secretario novoSecretario = validador.validaCamposEntrada(cpf, nome, email, senha, senha, dataNascimento,
                telefone,
                endereco, deletadoEm, dataContratacao);
        novoSecretario.setId(id);

        Secretario existingSecretario = repositorio.buscarPorCpf(novoSecretario.getCpf());
        if (existingSecretario != null && existingSecretario.getId() != id) {
            throw new GestanteException("ERRO: Já existe um secretário cadastrado com esse cpf.");
        }
        existingSecretario = repositorio.buscarPorEmail(novoSecretario.getEmail());
        if (existingSecretario != null && existingSecretario.getId() != id) {
            throw new GestanteException("ERRO: Já existe um secretário cadastrada com esse email.");
        }

        repositorio.editar(novoSecretario);
    }

    public void deletar(int id, int idAutenticado) {
        Secretario secretario = repositorio.buscar(id);
        validador.validaExclusão(secretario, idAutenticado);
        repositorio.deletar(secretario);
    }

    public void atualizarSenha(Secretario usuario, String senha) {
        String senhaValidada = validador.validaSenha(senha);
        String hashSenha = gerenciadorCriptografia.criptografarSenha(senhaValidada);
        usuario.setSenha(hashSenha);
        repositorio.editar(usuario);
    }

    public Secretario adicionarCodigoRecuperacao(String cpf, String codigo) {
        Secretario secretario = repositorio.buscarPorCpf(cpf);

        if (secretario == null) {
            throw new GestanteException("ERRO: Não foi encontrado um(a) secretário(a) com esse cpf.");
        }

        if (secretario != null) {
            secretario.setCodigoRecuperacao(codigo);
            secretario.setValidadeCodigoRecuperacao(LocalDateTime.now().plusMinutes(30));
            repositorio.editar(secretario);
            notificador.notificar(secretario, "BemGestar | Recuperação de Senha", "Seu código de recuperação é: "
                    + codigo
                    + ". Pelos próximos 30 minutos, você vai conseguir logar na sua conta utilizando este código no lugar da senha. Entre na sua conta e seleciona a opção de mudar senha para redefinir sua senha.");
        }
        return secretario;
    }

}
