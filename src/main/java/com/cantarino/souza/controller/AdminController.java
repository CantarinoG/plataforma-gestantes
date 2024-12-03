package com.cantarino.souza.controller;

import java.time.LocalDateTime;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMAdmin;
import com.cantarino.souza.model.dao.AdminDao;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.exceptions.GestanteException;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateAdmin;

public class AdminController {

    private AdminDao repositorio;
    private ValidateAdmin validator;
    private NotificadorEmail notificador;

    public AdminController() {
        repositorio = new AdminDao();
        validator = new ValidateAdmin();
        notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMAdmin(repositorio.findAll()), null);
    }

    public void cadastrar(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm) {

        Admin novoAdm = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm);
        String hashSenha = Util.hashPassword(novoAdm.getSenha());
        novoAdm.setSenha(hashSenha);

        Admin existingAdmin = repositorio.findByCpf(novoAdm.getCpf());
        if (existingAdmin != null) {
            throw new GestanteException("Já existe um administrador cadastrado com esse cpf.");
        }
        existingAdmin = repositorio.findByEmail(novoAdm.getEmail());
        if (existingAdmin != null) {
            throw new GestanteException("Já existe um administrador cadastrada com esse email.");
        }

        repositorio.save(novoAdm);
    }

    public Admin buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void atualizar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm) {
        Admin novoAdm = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm);
        novoAdm.setId(id);

        Admin existingAdmin = repositorio.findByCpf(novoAdm.getCpf());
        if (existingAdmin != null && existingAdmin.getId() != id) {
            throw new GestanteException("Já existe um administrador cadastrado com esse cpf.");
        }
        existingAdmin = repositorio.findByEmail(novoAdm.getEmail());
        if (existingAdmin != null && existingAdmin.getId() != id) {
            throw new GestanteException("Já existe um administrador cadastrada com esse email.");
        }

        repositorio.update(novoAdm);
    }

    public void excluir(int id) {
        Admin adm = repositorio.find(id);
        repositorio.delete(adm);
    }

    public void atualizaSenha(Admin usuario, String senha) {
        String senhaValidada = validator.validaSenha(senha);
        String hashSenha = Util.hashPassword(senhaValidada);
        usuario.setSenha(hashSenha);
        repositorio.update(usuario);
    }

    public Admin adicionarCodigoRecuperacao(String cpf, String codigo) {
        Admin admin = repositorio.findByCpf(cpf);
        if (admin != null) {
            admin.setCodigoRecuperacao(codigo);
            admin.setValidadeCodigoRecuperacao(LocalDateTime.now().plusMinutes(30));
            repositorio.update(admin);
            notificador.notificar(admin, "BemGestar | Recuperação de Senha", "Seu código de recuperação é: "
                    + codigo
                    + ". Pelos próximos 30 minutos, você vai conseguir logar na sua conta utilizando este código no lugar da senha. Entre na sua conta e seleciona a opção de mudar senha para redefinir sua senha.");
        }
        return admin;
    }

}
