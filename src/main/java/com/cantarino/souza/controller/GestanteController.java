package com.cantarino.souza.controller;

import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMGestantes;
import com.cantarino.souza.model.dao.GestanteDao;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.valid.ValidateGestante;

public class GestanteController {

    private GestanteDao repositorio;
    private ValidateGestante validator;

    public GestanteController() {
        repositorio = new GestanteDao();
        validator = new ValidateGestante();
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

}
