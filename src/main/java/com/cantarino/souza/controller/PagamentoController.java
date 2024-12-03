package com.cantarino.souza.controller;

import com.cantarino.souza.controller.tablemodels.TMPagamento;
import com.cantarino.souza.model.dao.PagamentoDao;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.model.exceptions.PagamentoException;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidatePagamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTable;

public class PagamentoController {
    private PagamentoDao repositorio;
    private ValidatePagamento validator;
    private NotificadorEmail notificador;

    public PagamentoController() {
        this.repositorio = new PagamentoDao();
        this.validator = new ValidatePagamento();
        this.notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMPagamento(repositorio.findAll()), null);
    }

    public void cadastrar(String valor, Usuario registradoPor, Gestante paciente, String metodoPagamento,
            Procedimento procedimento, String deletadoEm) {
        if (registradoPor == null) {
            throw new PagamentoException("Usuário que registrou não pode ser nulo");
        }
        if (paciente == null) {
            throw new PagamentoException("Paciente não pode ser nulo");
        }
        if (procedimento == null) {
            throw new PagamentoException("Procedimento não pode ser nulo");
        }

        if (procedimento.getStatus().equals(StatusProcedimentos.CANCELADA.getValue())) {
            throw new PagamentoException("Não é possível registrar pagamento para um procedimento cancelado");
        }

        Pagamento pagamentoExistente = buscarPorIdProcedimento(procedimento.getId());
        if (pagamentoExistente != null) {
            throw new PagamentoException("Já existe um pagamento registrado para este procedimento");
        }

        double valorPago = Double.parseDouble(valor);
        if (valorPago > procedimento.getValor()) {
            throw new PagamentoException("O valor informado não pode ser maior que o valor do procedimento");
        }

        Pagamento novoPagamento = validator.validaCamposEntrada(valor, metodoPagamento);
        novoPagamento.setRegistradoPor(registradoPor);
        novoPagamento.setPaciente(paciente);
        novoPagamento.setProcedimento(procedimento);
        repositorio.save(novoPagamento);

        String conteudoEmail = "Foi registrado um pagamento para o procedimento de id " + procedimento.getId() + ": "
                + procedimento.getDescricao() + ".";
        notificador.notificar(paciente, "Bem Gestar | Confirmação de Pagamento", conteudoEmail);
    }

    public void atualizar(int id, String valor, Usuario registradoPor, Gestante paciente,
            String metodoPagamento, Procedimento procedimento, String deletadoEm) {
        if (registradoPor == null) {
            throw new PagamentoException("Usuário que registrou não pode ser nulo");
        }
        if (paciente == null) {
            throw new PagamentoException("Paciente não pode ser nulo");
        }
        if (procedimento == null) {
            throw new PagamentoException("Procedimento não pode ser nulo");
        }

        double valorPago = Double.parseDouble(valor);
        if (valorPago > procedimento.getValor()) {
            throw new PagamentoException("O valor informado não pode ser maior que o valor do procedimento");
        }

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

    public List<Pagamento> buscarTodas() {
        return repositorio.findAll();
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
