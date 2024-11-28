package com.cantarino.souza.controller;

import java.time.LocalDateTime;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMRelatorio;
import com.cantarino.souza.model.dao.RelatorioDao;
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.valid.ValidateRelatorio;

public class RelatorioController {
    private RelatorioDao repositorio;
    private ValidateRelatorio validator;

    public RelatorioController() {
        this.repositorio = new RelatorioDao();
        this.validator = new ValidateRelatorio();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMRelatorio(repositorio.findAll()), null);
    }

    public int cadastrar(LocalDateTime dataEmissao, String resultado, String observacoes, String caminhoPdf,
            LocalDateTime deletadoEm) {
        Relatorio novoRelatorio = validator.validaCamposEntrada(resultado, observacoes, caminhoPdf);
        novoRelatorio.setDataEmissao(dataEmissao);
        repositorio.save(novoRelatorio);
        return novoRelatorio.getId();
    }

    public void atualizar(int id, LocalDateTime dataEmissao, String resultado, String observacoes, String caminhoPdf,
            LocalDateTime deletadoEm) {
        Relatorio novoRelatorio = validator.validaCamposEntrada(resultado, observacoes, caminhoPdf);
        novoRelatorio.setId(id);
        novoRelatorio.setDataEmissao(dataEmissao);
        repositorio.update(novoRelatorio);
    }

    public void excluir(int id) {
        Relatorio relatorio = repositorio.find(id);
        repositorio.delete(relatorio);
    }

    public Relatorio buscarPorId(int id) {
        return repositorio.find(id);
    }
}
