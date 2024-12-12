package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMGestantes;
import com.cantarino.souza.model.dao.GestanteDao;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.exceptions.GestanteException;
import com.cantarino.souza.model.utils.GerenciadorCriptografia;
import com.cantarino.souza.model.utils.INotificador;
import com.cantarino.souza.model.utils.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateGestante;

public class GestanteController {

    private GestanteDao repositorio;
    private ValidateGestante validador;
    private INotificador notificador;
    private GerenciadorCriptografia gerenciadorCriptografia;

    public GestanteController() {
        repositorio = new GestanteDao();
        validador = new ValidateGestante();
        notificador = new NotificadorEmail();
        gerenciadorCriptografia = new GerenciadorCriptografia();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMGestantes(repositorio.buscarTodos()), null);
    }

    public void salvar(String cpf, String nome, String email, String senha, String senhaConfirmada,
            String dataNascimento,
            String telefone, String endereco, String deletadoEm, String previsaoParto, String contatoEmergencia,
            String historicoMedico, String tipoSanguineo) {

        Gestante novaGestante = validador.validaCamposEntrada(cpf, nome, email, senha, senhaConfirmada, dataNascimento,
                telefone,
                endereco, deletadoEm, previsaoParto, contatoEmergencia, historicoMedico, tipoSanguineo);

        String hashSenha = gerenciadorCriptografia.criptografarSenha(novaGestante.getSenha());
        novaGestante.setSenha(hashSenha);

        Gestante gestanteExistente = repositorio.buscarPorCpf(novaGestante.getCpf());
        if (gestanteExistente != null) {
            throw new GestanteException("ERRO: Já existe uma gestante cadastrada com esse cpf.");
        }
        gestanteExistente = repositorio.buscarPorEmail(novaGestante.getEmail());
        if (gestanteExistente != null) {
            throw new GestanteException("ERRO: Já existe uma gestante cadastrada com esse email.");
        }

        repositorio.salvar(novaGestante);

    }

    public Gestante buscar(int id) {
        return repositorio.buscar(id);
    }

    public List<Gestante> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void editar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String previsaoParto, String contatoEmergencia,
            String historicoMedico, String tipoSanguineo) {

        Gestante novaGestante = validador.validaCamposEntrada(cpf, nome, email, senha, senha, dataNascimento, telefone,
                endereco, deletadoEm, previsaoParto, contatoEmergencia, historicoMedico, tipoSanguineo);

        novaGestante.setId(id);

        Gestante gestanteExistente = repositorio.buscarPorCpf(novaGestante.getCpf());
        if (gestanteExistente != null && gestanteExistente.getId() != id) {
            throw new GestanteException("ERRO: Já existe uma gestante cadastrada com esse cpf.");
        }
        gestanteExistente = repositorio.buscarPorEmail(novaGestante.getEmail());
        if (gestanteExistente != null && gestanteExistente.getId() != id) {
            throw new GestanteException("ERRO: Já existe uma gestante cadastrada com esse email.");
        }

        repositorio.editar(novaGestante);

    }

    public void deletar(int id) {
        Gestante gestante = repositorio.buscar(id);
        repositorio.deletar(gestante);
    }

    public void atualizarSenha(Gestante usuario, String senha) {
        String senhaValidada = validador.validaSenha(senha);
        String hashSenha = gerenciadorCriptografia.criptografarSenha(senhaValidada);
        usuario.setSenha(hashSenha);
        repositorio.editar(usuario);
    }

    public Gestante adicionarCodigoRecuperacao(String cpf, String codigo) {
        Gestante gestante = repositorio.buscarPorCpf(cpf);

        if (gestante == null) {
            throw new GestanteException("ERRO: Não foi encontrada uma gestante com esse cpf.");
        }

        gestante.setCodigoRecuperacao(codigo);
        gestante.setValidadeCodigoRecuperacao(LocalDateTime.now().plusMinutes(30));
        repositorio.editar(gestante);
        notificador.notificar(gestante, "BemGestar | Recuperação de Senha", "Seu código de recuperação é: " + codigo
                + ". Pelos próximos 30 minutos, você vai conseguir logar na sua conta utilizando este código no lugar da senha. Entre na sua conta e seleciona a opção de mudar senha para redefinir sua senha.");
        return gestante;
    }

}
