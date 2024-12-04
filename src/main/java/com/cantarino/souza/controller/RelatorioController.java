package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.io.File;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMRelatorio;
import com.cantarino.souza.model.dao.RelatorioDao;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.services.GerenciadorPdf;
import com.cantarino.souza.model.valid.ValidateRelatorio;

public class RelatorioController {
    private RelatorioDao repositorio;
    private ValidateRelatorio validador;
    private GerenciadorPdf gerenciadorPdf;

    public RelatorioController() {
        this.repositorio = new RelatorioDao();
        this.validador = new ValidateRelatorio();
        this.gerenciadorPdf = new GerenciadorPdf();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMRelatorio(repositorio.buscarTodos()), null);
    }

    public int salvar(LocalDateTime dataEmissao, String resultado, String observacoes, String caminhoPdf,
            LocalDateTime deletadoEm) {

        Relatorio novoRelatorio = validador.validaCamposEntrada(resultado, observacoes, caminhoPdf, dataEmissao);

        repositorio.salvar(novoRelatorio);
        return novoRelatorio.getId();
    }

    public void editar(int id, LocalDateTime dataEmissao, String resultado, String observacoes, String caminhoPdf,
            LocalDateTime deletadoEm) {

        Relatorio novoRelatorio = validador.validaCamposEntrada(resultado, observacoes, caminhoPdf, dataEmissao);
        novoRelatorio.setId(id);

        repositorio.editar(novoRelatorio);
    }

    public void deletar(int id) {
        Relatorio relatorio = repositorio.buscar(id);
        repositorio.deletar(relatorio);
    }

    public Relatorio buscar(int id) {
        return repositorio.buscar(id);
    }

    public void gerarPdf(String caminho, int id, Procedimento procedimento) {
        Relatorio relatorio = repositorio.buscar(id);
        String caminhoTemporario = "storage/relatorios/temp";

        new File(caminhoTemporario).mkdirs();

        gerenciadorPdf.gerarPdf(caminhoTemporario,
                "PDF gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "\n",
                "Bem Gestar" + "\n",
                "Relatório de Procedimento", "\n",
                "Id do Relatório: " + relatorio.getId(),
                "Data de Emissão: "
                        + relatorio.getDataEmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                "\n",
                "Id do procedimento: " + procedimento.getId(),
                "Paciente: " + procedimento.getPaciente().getNome(),
                "Descrição: " + procedimento.getDescricao(),
                "Data do Procedimento: "
                        + procedimento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                "Status: " + procedimento.getStatus() + "\n\n" + "Resultado: " + relatorio.getResultado(),
                "Observações: " + relatorio.getObeservacoes());
        String caminhoArquivo = caminhoTemporario + "/recibo.pdf";
        List<String> caminhosEntrada = Arrays.asList(caminhoArquivo, relatorio.getCaminhoPdf());
        gerenciadorPdf.combinarPdfs(caminho, caminhosEntrada);
    }
}
