package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMGestantes;
import com.cantarino.souza.model.dao.GestanteDao;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.exceptions.GestanteException;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateGestante;

public class GestanteController {

    private GestanteDao repositorio;
    private ValidateGestante validator;
    private NotificadorEmail notificador;

    public GestanteController() {
        repositorio = new GestanteDao();
        validator = new ValidateGestante();
        notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMGestantes(repositorio.findAll()), null);
    }

    public void cadastrar(String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String previsaoParto, String contatoEmergencia,
            String historicoMedico, String tipoSanguineo) {

        Gestante novaGestante = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone,
                endereco, deletadoEm, previsaoParto, contatoEmergencia, historicoMedico, tipoSanguineo);

        String hashSenha = Util.hashPassword(novaGestante.getSenha());
        novaGestante.setSenha(hashSenha);

        Gestante existingGestante = repositorio.findByCpf(novaGestante.getCpf());
        if (existingGestante != null) {
            throw new GestanteException("Já existe uma gestante cadastrada com esse cpf.");
        }
        existingGestante = repositorio.findByEmail(novaGestante.getEmail());
        if (existingGestante != null) {
            throw new GestanteException("Já existe uma gestante cadastrada com esse email.");
        }

        repositorio.save(novaGestante);

    }

    public Gestante buscarPorId(int id) {
        return repositorio.find(id);
    }

    public List<Gestante> buscarTodas() {
        return repositorio.findAll();
    }

    public void atualizar(int id, String cpf, String nome, String email, String senha, String dataNascimento,
            String telefone, String endereco, String deletadoEm, String previsaoParto, String contatoEmergencia,
            String historicoMedico, String tipoSanguineo) {

        Gestante novaGestante = validator.validaCamposEntrada(cpf, nome, email, senha, dataNascimento, telefone,
                endereco, deletadoEm, previsaoParto, contatoEmergencia, historicoMedico, tipoSanguineo);

        novaGestante.setId(id);

        Gestante existingGestante = repositorio.findByCpf(novaGestante.getCpf());
        if (existingGestante != null && existingGestante.getId() != id) {
            throw new GestanteException("Já existe uma gestante cadastrada com esse cpf.");
        }
        existingGestante = repositorio.findByEmail(novaGestante.getEmail());
        if (existingGestante != null && existingGestante.getId() != id) {
            throw new GestanteException("Já existe uma gestante cadastrada com esse email.");
        }

        repositorio.update(novaGestante);

    }

    public void excluir(int id) {
        Gestante gestante = repositorio.find(id);
        repositorio.delete(gestante);
    }

    public void atualizaSenha(Gestante usuario, String senha) {
        String senhaValidada = validator.validaSenha(senha);
        String hashSenha = Util.hashPassword(senhaValidada);
        usuario.setSenha(hashSenha);
        repositorio.update(usuario);
    }

    public Gestante adicionarCodigoRecuperacao(String cpf, String codigo) {
        Gestante gestante = repositorio.findByCpf(cpf);
        if (gestante != null) {
            gestante.setCodigoRecuperacao(codigo);
            gestante.setValidadeCodigoRecuperacao(LocalDateTime.now().plusMinutes(30));
            repositorio.update(gestante);
            notificador.notificar(gestante, "BemGestar | Recuperação de Senha", "Seu código de recuperação é: " + codigo
                    + ". Pelos próximos 30 minutos, você vai conseguir logar na sua conta utilizando este código no lugar da senha. Entre na sua conta e seleciona a opção de mudar senha para redefinir sua senha.");
        }
        return gestante;
    }

}
