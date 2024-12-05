package com.cantarino.souza.controller;

import java.time.LocalDateTime;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMAdmin;
import com.cantarino.souza.model.dao.AdminDao;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.exceptions.GestanteException;
import com.cantarino.souza.model.services.GerenciadorCriptografia;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateAdmin;

public class AdminController {

    private AdminDao repositorio;
    private ValidateAdmin validador;
    private NotificadorEmail notificador;
    private GerenciadorCriptografia gerenciadorCriptografia;

    public AdminController() {
        repositorio = new AdminDao();
        validador = new ValidateAdmin();
        notificador = new NotificadorEmail();
        gerenciadorCriptografia = new GerenciadorCriptografia();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMAdmin(repositorio.buscarTodos()), null);
    }

    public Admin buscar(int id) {
        return repositorio.buscar(id);
    }

    public void atualizarSenha(Admin usuario, String senha) {
        String senhaValidada = validador.validaSenha(senha);
        String hashSenha = gerenciadorCriptografia.criptografarSenha(senhaValidada);
        usuario.setSenha(hashSenha);
        repositorio.editar(usuario);
    }

    public Admin adicionarCodigoRecuperacao(String cpf, String codigo) {
        Admin admin = repositorio.buscarPorCpf(cpf);

        if (admin == null) {
            throw new GestanteException("ERRO: Não foi encontrado um(a) administrador(a) com esse cpf.");
        }

        if (admin != null) {
            admin.setCodigoRecuperacao(codigo);
            admin.setValidadeCodigoRecuperacao(LocalDateTime.now().plusMinutes(30));
            repositorio.editar(admin);
            notificador.notificar(admin, "BemGestar | Recuperação de Senha", "Seu código de recuperação é: "
                    + codigo
                    + ". Pelos próximos 30 minutos, você vai conseguir logar na sua conta utilizando este código no lugar da senha. Entre na sua conta e seleciona a opção de mudar senha para redefinir sua senha.");
        }
        return admin;
    }

    public void salvar(String cpf, String nome, String email, String senha, String senhaConfirmada,
            String dataNascimento,
            String telefone, String endereco, String deletadoEm) {

        Admin novoAdm = validador.validaCamposEntrada(cpf, nome, email, senha, senhaConfirmada, dataNascimento,
                telefone, endereco,
                deletadoEm);
        String hashSenha = gerenciadorCriptografia.criptografarSenha(novoAdm.getSenha());
        novoAdm.setSenha(hashSenha);

        Admin admExistente = repositorio.buscarPorCpf(novoAdm.getCpf());
        if (admExistente != null) {
            throw new GestanteException("ERRO: Já existe um administrador cadastrado com esse cpf.");
        }
        admExistente = repositorio.buscarPorEmail(novoAdm.getEmail());
        if (admExistente != null) {
            throw new GestanteException("ERRO: Já existe um administrador cadastrada com esse email.");
        }

        repositorio.salvar(novoAdm);
    }

    public void editar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm) {
        Admin novoAdm = validador.validaCamposEntrada(cpf, nome, email, senha, senha, dataNascimento, telefone,
                endereco,
                deletadoEm);
        novoAdm.setId(id);

        Admin admExistente = repositorio.buscarPorCpf(novoAdm.getCpf());
        if (admExistente != null && admExistente.getId() != id) {
            throw new GestanteException("ERRO: Já existe um administrador cadastrado com esse cpf.");
        }
        admExistente = repositorio.buscarPorEmail(novoAdm.getEmail());
        if (admExistente != null && admExistente.getId() != id) {
            throw new GestanteException("ERRO: Já existe um administrador cadastrada com esse email.");
        }

        repositorio.editar(novoAdm);
    }

    public void deletar(int id, int idAutenticado) {
        Admin adm = repositorio.buscar(id);
        validador.validaExclusão(adm, idAutenticado);
        repositorio.deletar(adm);
    }

}
