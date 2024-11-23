package com.cantarino.souza.controller;

import com.cantarino.souza.controller.tablemodels.TMPagamento;
import com.cantarino.souza.model.dao.PagamentoDao;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.valid.ValidatePagamento;
import javax.swing.JTable;

public class PagamentoController {
    private PagamentoDao repositorio;
    private ValidatePagamento validator;

    public PagamentoController() {
        this.repositorio = new PagamentoDao();
        this.validator = new ValidatePagamento();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMPagamento(repositorio.findAll()), null);
    }

    public void cadastrar(String valor, Usuario registradoPor, Gestante paciente, String status, String metodoPagamento,
            String deletadoEm) {
        Pagamento novoPagamento = validator.validaCamposEntrada(valor, status, metodoPagamento);
        novoPagamento.setRegistradoPor(registradoPor);
        novoPagamento.setPaciente(paciente);
        repositorio.save(novoPagamento);
    }

    public void atualizar(int id, String valor, Usuario registradoPor, Gestante paciente, String status,
            String metodoPagamento, String deletadoEm) {
        Pagamento novoPagamento = validator.validaCamposEntrada(valor, status, metodoPagamento);
        novoPagamento.setId(id);
        novoPagamento.setRegistradoPor(registradoPor);
        novoPagamento.setPaciente(paciente);
        repositorio.update(novoPagamento);
    }

    public void excluir(int id) {
        Pagamento pagamento = repositorio.find(id);
        repositorio.delete(pagamento);
    }
}
