package com.cantarino.souza.controller;

import com.cantarino.souza.controller.tablemodels.TMPagamento;
import com.cantarino.souza.model.dao.PagamentoDao;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.valid.ValidatePagamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Pagamento buscarPorIdProcedimento(int id) {
        return repositorio.filterProcedimentoId(id);
    }

    public void excluir(int id) {
        Pagamento pagamento = repositorio.find(id);
        repositorio.delete(pagamento);
    }

    public void filtrarTabelaPorIdPaciente(JTable grd, int id) {
        Util.jTableShow(grd, new TMPagamento(repositorio.filterGestanteId(id)), null);
    }

    public void gerarRecibo(String path, int id) {
        Pagamento pagamento = repositorio.find(id);
        Util.generatePdf(path,
                "PDF gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "\n",
                "Bem Gestar" + "\n",
                "Recibo de Pagamento", "\n",
                "Id do Pagamento: " + pagamento.getId(), "Valor Total: " + pagamento.getProcedimento().getValor(),
                "Valor Pago: " + pagamento.getValor(), "Paciente: " + pagamento.getPaciente().getNome(),
                "Pagamento Registrado Por: " + pagamento.getRegistradoPor().getNome(),
                "Método de Pagamento: " + pagamento.getMetodoPagamento(), "\n",
                "Id do Procedimento: " + pagamento.getProcedimento().getId(),
                "Procedimento: " + pagamento.getProcedimento().getDescricao(),
                "Data do Procedimento: "
                        + pagamento.getProcedimento().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                "Status do Procedimento: " + pagamento.getProcedimento().getStatus());
    }
}
