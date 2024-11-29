package com.cantarino.souza.controller;

import java.io.FileOutputStream;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import org.mindrot.jbcrypt.BCrypt;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import java.io.File;
import java.util.List;

public class Util {

    public static void jTableShow(JTable grd, AbstractTableModel tableModel, TableCellRenderer render) {
        grd.setModel(tableModel);
        if (render != null) {
            grd.setDefaultRenderer(Object.class, render);
        }
    }

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static void generatePdf(String path, String... content) {
        Document document = new Document();
        try {
            String filePath = path + "/recibo.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            for (String text : content) {
                document.add(new Paragraph(text));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static void mergePdfs(String outputPath, List<String> inputPaths) {
        Document document = new Document();
        try {
            if (new File(outputPath).isDirectory()) {
                outputPath = outputPath + File.separator + "relatorio.pdf";
            }

            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();

            PdfCopy copy = new PdfCopy(document, new FileOutputStream(outputFile));
            document.open();

            for (String inputPath : inputPaths) {
                PdfReader reader = new PdfReader(inputPath);
                copy.addDocument(reader);
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

}
