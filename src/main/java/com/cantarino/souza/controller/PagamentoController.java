package com.cantarino.souza.controller;

import com.cantarino.souza.controller.tablemodels.TMPagamento;
import com.cantarino.souza.model.dao.PagamentoDao;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Procedimento;
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

    public void cadastrar(String valor, Usuario registradoPor, Gestante paciente, String metodoPagamento,
            Procedimento procedimento, String deletadoEm) {
        Pagamento novoPagamento = validator.validaCamposEntrada(valor, metodoPagamento);
        novoPagamento.setRegistradoPor(registradoPor);
        novoPagamento.setPaciente(paciente);
        novoPagamento.setProcedimento(procedimento);
        repositorio.save(novoPagamento);
    }

    public void atualizar(int id, String valor, Usuario registradoPor, Gestante paciente,
            String metodoPagamento, Procedimento procedimento, String deletadoEm) {
        Pagamento novoPagamento = validator.validaCamposEntrada(valor, metodoPagamento);
        novoPagamento.setId(id);
        novoPagamento.setRegistradoPor(registradoPor);
        novoPagamento.setPaciente(paciente);
        novoPagamento.setProcedimento(procedimento);
        repositorio.update(novoPagamento);
    }

    public Pagamento buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void excluir(int id) {
        Pagamento pagamento = repositorio.find(id);
        repositorio.delete(pagamento);
    }

    public void filtrarTabelaPorIdPaciente(JTable grd, int id) {
        Util.jTableShow(grd, new TMPagamento(repositorio.filterGestanteId(id)), null);
    }
}
