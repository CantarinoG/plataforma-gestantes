package com.cantarino.souza.model.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class GerenciadorPdf {

    public void gerarPdf(String caminho, String... conteudo) {
        Document documento = new Document();
        try {
            String caminhoArquivo = caminho + "/recibo.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(caminhoArquivo));
            documento.open();

            for (String texto : conteudo) {
                documento.add(new Paragraph(texto));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }

    public void combinarPdfs(String caminhoSaida, List<String> caminhosEntrada) {
        Document documento = new Document();
        try {
            if (new File(caminhoSaida).isDirectory()) {
                caminhoSaida = caminhoSaida + File.separator + "relatorio.pdf";
            }

            File arquivoSaida = new File(caminhoSaida);
            arquivoSaida.getParentFile().mkdirs();

            PdfCopy copia = new PdfCopy(documento, new FileOutputStream(arquivoSaida));
            documento.open();

            for (String inputPath : caminhosEntrada) {
                PdfReader reader = new PdfReader(inputPath);
                copia.addDocument(reader);
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }

}
