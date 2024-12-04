package com.cantarino.souza.model.valid;

import java.time.LocalDateTime;

import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.exceptions.RelatorioException;

public class ValidateRelatorio {

    private boolean caminhoValido(String caminho) {
        try {
            @SuppressWarnings("unused")
            java.nio.file.Path normalizedPath = java.nio.file.Paths.get(caminho).normalize();
            return true;
        } catch (java.nio.file.InvalidPathException e) {
            return false;
        }
    }

    public Relatorio validaCamposEntrada(String resultado, String observacoes, String caminhoPdf,
            LocalDateTime dataEmissao) {
        Relatorio relatorio = new Relatorio();

        if (resultado == null || resultado.isEmpty()) {
            throw new RelatorioException("ERRO: Campo resultado não pode ser vazio.");
        }
        relatorio.setResultado(resultado);

        if (observacoes != null) {
            relatorio.setObeservacoes(observacoes);
        }

        if (caminhoPdf != null) {
            if (!caminhoValido(caminhoPdf)) {
                throw new RelatorioException("ERRO: Caminho do PDF não é válido.");
            }
            relatorio.setCaminhoPdf(caminhoPdf);
        }

        if (dataEmissao == null) {
            throw new RelatorioException("ERRO: Data de emissão não pode ser nula");
        }
        relatorio.setDataEmissao(dataEmissao);

        return relatorio;
    }
}
