package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMMedico;
import com.cantarino.souza.model.dao.MedicoDao;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateMedico;

public class MedicoController {

    private MedicoDao repositorio;
    private ValidateMedico validator;
    private NotificadorEmail notificador;

    public MedicoController() {
        repositorio = new MedicoDao();
        validator = new ValidateMedico();
        notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMMedico(repositorio.findAll()), null);
    }

    public void cadastrar(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String especializacao, String crm) {

        Medico novoMedico = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm, especializacao, crm);
        String hashSenha = Util.hashPassword(novoMedico.getSenha());
        novoMedico.setSenha(hashSenha);
        repositorio.save(novoMedico);

    }

    public Medico buscarPorId(int id) {
        return repositorio.find(id);
    }

    public List<Medico> buscarTodas() {
        return repositorio.findAll();
    }

    public void atualizar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String especializacao, String crm) {
        Medico novoMedico = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone, endereco,
                deletadoEm, especializacao, crm);
        novoMedico.setId(id);
        repositorio.update(novoMedico);
    }

    public void excluir(int id) {
        Medico medico = repositorio.find(id);
        repositorio.delete(medico);
    }

    public void atualizaSenha(Medico usuario, String senha) {
        String senhaValidada = validator.validaSenha(senha);
        String hashSenha = Util.hashPassword(senhaValidada);
        usuario.setSenha(hashSenha);
        repositorio.update(usuario);
    }

    public Medico adicionarCodigoRecuperacao(String cpf, String codigo) {
        Medico medico = repositorio.findByCpf(cpf);
        if (medico != null) {
            medico.setCodigoRecuperacao(codigo);
            medico.setValidadeCodigoRecuperacao(LocalDateTime.now().plusMinutes(30));
            repositorio.update(medico);
            notificador.notificar(medico, "BemGestar | Recuperação de Senha", "Seu código de recuperação é: " + codigo
                    + ". Pelos próximos 30 minutos, você vai conseguir logar na sua conta utilizando este código no lugar da senha. Entre na sua conta e seleciona a opção de mudar senha para redefinir sua senha.");
        }
        return medico;
    }

}
