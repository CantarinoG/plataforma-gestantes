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

public class AutenticacaoController {

    private GestanteDao repositorioGestante;
    private MedicoDao repositorioMedico;
    private SecretarioDao repositorioSecretario;
    private AdminDao repositorioAdmin;
    private Autenticacao autenticacao;

    public AutenticacaoController() {
        this.repositorioGestante = new GestanteDao();
        this.repositorioMedico = new MedicoDao();
        this.repositorioSecretario = new SecretarioDao();
        this.repositorioAdmin = new AdminDao();
        autenticacao = Autenticacao.getInstance();
    }

    public void logInGestante(String cpf, String senha) {
        Gestante gestante = repositorioGestante.findByCpf(cpf);
        if (gestante == null) {
            throw new GestanteException("ERRO: Não existe nenhuma gestante com esse email.");
        }

        if (gestante.getCodigoRecuperacao() != null && gestante.getValidadeCodigoRecuperacao() != null) {
            if (gestante.getCodigoRecuperacao().equals(senha)
                    && gestante.getValidadeCodigoRecuperacao().isAfter(LocalDateTime.now())) {
                autenticacao.setUsuario(gestante);
                return;
            }
        }

        if (!Util.checkPassword(senha, gestante.getSenha())) {
            throw new GestanteException("ERRO: A senha informada está incorreta.");
        }

        autenticacao.setUsuario(gestante);
    }

    public void logInMedico(String cpf, String senha) {
        Medico medico = repositorioMedico.findByCpf(cpf);
        if (medico == null) {
            throw new MedicoException("ERRO: Não existe nenhum médico com esse email.");
        }

        if (medico.getCodigoRecuperacao() != null && medico.getValidadeCodigoRecuperacao() != null) {
            if (medico.getCodigoRecuperacao().equals(senha)
                    && medico.getValidadeCodigoRecuperacao().isAfter(LocalDateTime.now())) {
                autenticacao.setUsuario(medico);
                return;
            }
        }

        if (!Util.checkPassword(senha, medico.getSenha())) {
            throw new MedicoException("ERRO: A senha informada está incorreta.");
        }

        autenticacao.setUsuario(medico);
    }

    public void logInSecretario(String cpf, String senha) {
        Secretario secretario = repositorioSecretario.findByCpf(cpf);
        if (secretario == null) {
            throw new SecretarioException("ERRO: Não existe nenhum secretário com esse email.");
        }

        if (secretario.getCodigoRecuperacao() != null && secretario.getValidadeCodigoRecuperacao() != null) {
            if (secretario.getCodigoRecuperacao().equals(senha)
                    && secretario.getValidadeCodigoRecuperacao().isAfter(LocalDateTime.now())) {
                autenticacao.setUsuario(secretario);
                return;
            }
        }

        if (!Util.checkPassword(senha, secretario.getSenha())) {
            throw new SecretarioException("ERRO: A senha informada está incorreta.");
        }

        autenticacao.setUsuario(secretario);
    }

    public void logInAdmin(String cpf, String senha) {
        Admin admin = repositorioAdmin.findByCpf(cpf);
        if (admin == null) {
            throw new AdminException("ERRO: Não existe nenhum administrador com esse email.");
        }

        if (admin.getCodigoRecuperacao() != null && admin.getValidadeCodigoRecuperacao() != null) {
            if (admin.getCodigoRecuperacao().equals(senha)
                    && admin.getValidadeCodigoRecuperacao().isAfter(LocalDateTime.now())) {
                autenticacao.setUsuario(admin);
                return;
            }
        }

        if (!Util.checkPassword(senha, admin.getSenha())) {
            throw new AdminException("ERRO: A senha informada está incorreta.");
        }

        autenticacao.setUsuario(admin);
    }

    public void logOut() {
        autenticacao.setUsuario(null);
    }

    public Usuario getUsuario() {
        return autenticacao.getUsuario();
    }

}
