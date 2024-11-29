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

    public void gerarPdf(String path, int id, Procedimento procedimento) {
        Relatorio relatorio = repositorio.find(id);
        String tempPath = "storage/relatorios/temp";

        new File(tempPath).mkdirs();

        Util.generatePdf(tempPath,
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
        String filePath = tempPath + "/recibo.pdf";
        List<String> inputPaths = Arrays.asList(filePath, relatorio.getCaminhoPdf());
        Util.mergePdfs(path, inputPaths);
    }
}
