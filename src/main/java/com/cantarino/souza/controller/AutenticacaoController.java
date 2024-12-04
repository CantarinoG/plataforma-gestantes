package com.cantarino.souza.controller;

import java.time.LocalDateTime;

import com.cantarino.souza.model.dao.AdminDao;
import com.cantarino.souza.model.dao.GestanteDao;
import com.cantarino.souza.model.dao.MedicoDao;
import com.cantarino.souza.model.dao.SecretarioDao;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.AdminException;
import com.cantarino.souza.model.exceptions.GestanteException;
import com.cantarino.souza.model.exceptions.MedicoException;
import com.cantarino.souza.model.exceptions.SecretarioException;
import com.cantarino.souza.model.services.Autenticacao;
import com.cantarino.souza.model.services.GerenciadorCriptografia;

public class AutenticacaoController {

    private GestanteDao repositorioGestante;
    private MedicoDao repositorioMedico;
    private SecretarioDao repositorioSecretario;
    private AdminDao repositorioAdmin;
    private Autenticacao autenticacao;
    private GerenciadorCriptografia gerenciadorCriptografia;

    public AutenticacaoController() {
        this.repositorioGestante = new GestanteDao();
        this.repositorioMedico = new MedicoDao();
        this.repositorioSecretario = new SecretarioDao();
        this.repositorioAdmin = new AdminDao();
        this.autenticacao = Autenticacao.getInstance();
        this.gerenciadorCriptografia = new GerenciadorCriptografia();
    }

    private void realizarAutenticacao(Usuario usuario, String senha) {
        if (usuario.getCodigoRecuperacao() != null && usuario.getValidadeCodigoRecuperacao() != null) {
            if (usuario.getCodigoRecuperacao().equals(usuario.getCodigoRecuperacao())
                    && usuario.getValidadeCodigoRecuperacao().isAfter(LocalDateTime.now())) {
                autenticacao.setUsuario(usuario);
                return;
            }
        }

        if (!gerenciadorCriptografia.compararSenha(senha, usuario.getSenha())) {
            throw new GestanteException("ERRO: A senha informada está incorreta.");
        }

        autenticacao.setUsuario(usuario);
    }

    public void logarGestante(String cpf, String senha) {
        Gestante gestante = repositorioGestante.buscarPorCpf(cpf);
        if (gestante == null) {
            throw new GestanteException("ERRO: Não existe nenhuma gestante com esse cpf.");
        }

        realizarAutenticacao(gestante, senha);

    }

    public void logarMedico(String cpf, String senha) {
        Medico medico = repositorioMedico.buscarPorCpf(cpf);
        if (medico == null) {
            throw new MedicoException("ERRO: Não existe nenhum médico com esse cpf.");
        }

        realizarAutenticacao(medico, senha);

    }

    public void logarSecretario(String cpf, String senha) {
        Secretario secretario = repositorioSecretario.buscarPorCpf(cpf);
        if (secretario == null) {
            throw new SecretarioException("ERRO: Não existe nenhum secretário com esse cpf.");
        }

        realizarAutenticacao(secretario, senha);

    }

    public void logarAdmin(String cpf, String senha) {
        Admin admin = repositorioAdmin.buscarPorCpf(cpf);
        if (admin == null) {
            throw new AdminException("ERRO: Não existe nenhum administrador com esse cpf.");
        }

        realizarAutenticacao(admin, senha);

    }

    public void deslogar() {
        autenticacao.setUsuario(null);
    }

    public Usuario getUsuario() {
        return autenticacao.getUsuario();
    }

}
