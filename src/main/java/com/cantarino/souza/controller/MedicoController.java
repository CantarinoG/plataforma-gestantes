package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMMedico;
import com.cantarino.souza.model.dao.MedicoDao;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.exceptions.GestanteException;
import com.cantarino.souza.model.services.GerenciadorCriptografia;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateMedico;

public class MedicoController {

    private MedicoDao repositorio;
    private ValidateMedico validador;
    private NotificadorEmail notificador;
    private GerenciadorCriptografia gerenciadorCriptografia;

    public MedicoController() {
        repositorio = new MedicoDao();
        validador = new ValidateMedico();
        notificador = new NotificadorEmail();
        gerenciadorCriptografia = new GerenciadorCriptografia();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMMedico(repositorio.buscarTodos()), null);
    }

    public void salvar(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String especializacao, String crm) {

        Medico novoMedico = validador.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm, especializacao, crm);
        String hashSenha = gerenciadorCriptografia.criptografarSenha(novoMedico.getSenha());
        novoMedico.setSenha(hashSenha);

        Medico medicoExistente = repositorio.buscarPorCpf(novoMedico.getCpf());
        if (medicoExistente != null) {
            throw new GestanteException("ERRO: Já existe um médico cadastrado com esse cpf.");
        }
        medicoExistente = repositorio.buscarPorEmail(novoMedico.getEmail());
        if (medicoExistente != null) {
            throw new GestanteException("ERRO: Já existe um médico cadastrado com esse email.");
        }
        medicoExistente = repositorio.buscarPorCrm(novoMedico.getCrm());
        if (medicoExistente != null) {
            throw new GestanteException("ERRO: Já existe um médico cadastrado com esse CRM.");
        }

        repositorio.salvar(novoMedico);

    }

    public Medico buscar(int id) {
        return repositorio.buscar(id);
    }

    public List<Medico> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void editar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String especializacao, String crm) {
        Medico novoMedico = validador.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm, especializacao, crm);
        novoMedico.setId(id);

        Medico medicoExistente = repositorio.buscarPorCpf(novoMedico.getCpf());
        if (medicoExistente != null && medicoExistente.getId() != id) {
            throw new GestanteException("ERRO: Já existe um médico cadastrado com esse cpf.");
        }
        medicoExistente = repositorio.buscarPorEmail(novoMedico.getEmail());
        if (medicoExistente != null && medicoExistente.getId() != id) {
            throw new GestanteException("ERRO: Já existe um médico cadastrado com esse email.");
        }
        medicoExistente = repositorio.buscarPorCrm(novoMedico.getCrm());
        if (medicoExistente != null && medicoExistente.getId() != id) {
            throw new GestanteException("ERRO: Já existe um médico cadastrado com esse CRM.");
        }

        repositorio.editar(novoMedico);
    }

    public void deletar(int id) {
        Medico medico = repositorio.buscar(id);
        repositorio.deletar(medico);
    }

    public void atualizarSenha(Medico usuario, String senha) {
        String senhaValidada = validador.validaSenha(senha);
        String hashSenha = gerenciadorCriptografia.criptografarSenha(senhaValidada);
        usuario.setSenha(hashSenha);
        repositorio.editar(usuario);
    }

    public Medico adicionarCodigoRecuperacao(String cpf, String codigo) {
        Medico medico = repositorio.buscarPorCpf(cpf);
        if (medico != null) {
            medico.setCodigoRecuperacao(codigo);
            medico.setValidadeCodigoRecuperacao(LocalDateTime.now().plusMinutes(30));
            repositorio.editar(medico);
            notificador.notificar(medico, "BemGestar | Recuperação de Senha", "Seu código de recuperação é: " + codigo
                    + ". Pelos próximos 30 minutos, você vai conseguir logar na sua conta utilizando este código no lugar da senha. Entre na sua conta e seleciona a opção de mudar senha para redefinir sua senha.");
        }
        return medico;
    }

}
