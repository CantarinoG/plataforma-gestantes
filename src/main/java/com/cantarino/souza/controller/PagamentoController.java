package com.cantarino.souza.controller;

import com.cantarino.souza.controller.tablemodels.TMPagamento;
import com.cantarino.souza.model.dao.PagamentoDao;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.exceptions.PagamentoException;
import com.cantarino.souza.model.services.GerenciadorPdf;
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
    private GerenciadorPdf gerenciadorPdf;

    public PagamentoController() {
        this.repositorio = new PagamentoDao();
        this.validator = new ValidatePagamento();
        this.notificador = new NotificadorEmail();
        this.gerenciadorPdf = new GerenciadorPdf();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMPagamento(repositorio.buscarTodos()), null);
    }

    public void cadastrar(String valor, Usuario registradoPor, Gestante paciente, String metodoPagamento,
            Procedimento procedimento, String deletadoEm) {

        Pagamento pagamentoExistente = repositorio.buscarPorProcedimento(procedimento.getId());
        if (pagamentoExistente != null) {
            throw new PagamentoException("ERRO: Já existe um pagamento registrado para este procedimento");
        }

        Pagamento novoPagamento = validator.validaCamposEntrada(valor, metodoPagamento, registradoPor, paciente,
                procedimento);
        repositorio.salvar(novoPagamento);

        String conteudoEmail = "Foi registrado um pagamento para o procedimento de id " + procedimento.getId() + ": "
                + procedimento.getDescricao() + ".";
        notificador.notificar(paciente, "Bem Gestar | Confirmação de Pagamento", conteudoEmail);
    }

    public void atualizar(int id, String valor, Usuario registradoPor, Gestante paciente,
            String metodoPagamento, Procedimento procedimento, String deletadoEm) {

        Pagamento novoPagamento = validator.validaCamposEntrada(valor, metodoPagamento, registradoPor, paciente,
                procedimento);
        novoPagamento.setId(id);
        repositorio.editar(novoPagamento);
    }

    public Pagamento buscarPorId(int id) {
        return repositorio.buscar(id);
    }

    public List<Pagamento> buscarTodas() {
        return repositorio.buscarTodos();
    }

    public Pagamento buscarPorIdProcedimento(int id) {
        return repositorio.buscarPorProcedimento(id);
    }

    public void excluir(int id) {
        Pagamento pagamento = repositorio.buscar(id);
        repositorio.deletar(pagamento);
    }

    public void filtrarTabelaPorIdPaciente(JTable grd, int id) {
        Util.jTableShow(grd, new TMPagamento(repositorio.buscarPorGestante(id)), null);
    }

    public void gerarRecibo(String path, int id) {
        Pagamento pagamento = repositorio.buscar(id);
        gerenciadorPdf.gerarPdf(path,
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
