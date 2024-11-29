package com.cantarino.souza.view.screens;

import javax.swing.*;

import com.cantarino.souza.controller.RelatorioController;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.BackgroundPanel;
import com.cantarino.souza.view.components.RoundedButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class DlgDadosRelatorio extends JDialog {

    private JPanel panBackground;
    private JPanel panColumn;
    private JLabel lblAction;
    private JPanel panButton;
    private JButton btnEmitirPdf;
    private JLabel lblObservacoes;
    private JLabel lblResultado;
    private JPanel panDocumentoPdfContent;
    private JLabel lblArquivoSelecionado;
    private JButton btnVerDocumento;
    private JPanel panDocumentoPdfField;
    private JPanel panObservacoesField;
    private JPanel panResultadoField;

    private RelatorioController relatorioController;

    private Procedimento procedimento;

    public DlgDadosRelatorio(JDialog parent, boolean modal, Procedimento procedimento) {
        super(parent, modal);
        relatorioController = new RelatorioController();
        this.procedimento = procedimento;
        initComponents();

        lblResultado.setText(procedimento.getRelatorio().getResultado());
        lblObservacoes.setText(procedimento.getRelatorio().getObeservacoes());
        if (procedimento.getRelatorio().getCaminhoPdf() != null) {
            lblArquivoSelecionado.setText(procedimento.getRelatorio().getCaminhoPdf());
        }
    }

    private void initComponents() {
        setTitle("Visualizar Relatório");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1620, 930);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new GridBagLayout());
        setContentPane(panBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        lblAction = new JLabel("Visualizar Relatório");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(30, 0, 10, 0);
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(3, 1, 20, 5));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn, gbc);

        lblResultado = new JLabel();
        lblResultado.setFont(new Font("Arial", Font.PLAIN, 22));
        lblResultado.setBackground(AppColors.FIELD_PINK);
        lblResultado.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lblResultado.setOpaque(true);
        panResultadoField = createCustomLabelPanel("Resultado", lblResultado);
        panColumn.add(panResultadoField);

        panDocumentoPdfContent = new JPanel(new BorderLayout(10, 0));
        panDocumentoPdfContent.setBackground(AppColors.FIELD_PINK);

        lblArquivoSelecionado = new JLabel("Nenhum arquivo selecionado");
        lblArquivoSelecionado.setBackground(AppColors.FIELD_PINK);
        lblArquivoSelecionado.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        lblArquivoSelecionado.setFont(new Font("Arial", Font.PLAIN, 14));
        lblArquivoSelecionado.setOpaque(true);

        btnVerDocumento = new JButton("Ver Documento");
        btnVerDocumento.setBackground(AppColors.BUTTON_PINK);
        btnVerDocumento.setFont(new Font("Arial", Font.BOLD, 12));
        btnVerDocumento.setFocusPainted(false);
        btnVerDocumento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerDocumento.setPreferredSize(new Dimension(130, 30));
        btnVerDocumento.addActionListener(evt -> btnVerDocumentoActionPerformed(evt));

        panDocumentoPdfContent.add(lblArquivoSelecionado, BorderLayout.CENTER);
        panDocumentoPdfContent.add(btnVerDocumento, BorderLayout.EAST);

        panDocumentoPdfField = createCustomLabelPanel("Documento PDF", panDocumentoPdfContent);
        panColumn.add(panDocumentoPdfField);

        lblObservacoes = new JLabel();
        lblObservacoes.setFont(new Font("Arial", Font.PLAIN, 22));
        lblObservacoes.setBackground(AppColors.FIELD_PINK);
        lblObservacoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lblObservacoes.setOpaque(true);
        panObservacoesField = createCustomLabelPanel("Observações", lblObservacoes);

        panColumn.add(panObservacoesField);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 0.5;
        gbcButton.anchor = GridBagConstraints.NORTH;
        gbcButton.insets = new java.awt.Insets(10, 0, 0, 0);

        panButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panButton.setBackground(AppColors.TRANSPARENT);

        btnEmitirPdf = new RoundedButton("Emitir PDF",
                10);
        btnEmitirPdf.setPreferredSize(new Dimension(150, 50));
        btnEmitirPdf.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEmitirPdf.setForeground(Color.WHITE);
        btnEmitirPdf.addActionListener(evt -> btnEmitirPdfActionPerformed(evt));

        panButton.add(btnEmitirPdf);
        panBackground.add(panButton, gbcButton);
    }

    private JPanel createCustomLabelPanel(String hint, JComponent content) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        fieldPanel.setPreferredSize(new Dimension(500, 60));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel hintLabel = new JLabel(hint);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldPanel.add(hintLabel, BorderLayout.NORTH);

        JLabel contentLabel = new JLabel();
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contentLabel.setBackground(AppColors.FIELD_PINK);
        contentLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(content, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void btnEmitirPdfActionPerformed(ActionEvent evt) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Emitir PDF");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();
                System.out.println("Selected path: " + selectedPath);
                relatorioController.gerarPdf(selectedPath, procedimento.getRelatorio().getId(), procedimento);
            }
            JOptionPane.showMessageDialog(this, "Relatório Gerado com Sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar documento: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnVerDocumentoActionPerformed(ActionEvent evt) {
        try {
            File pdfFile = new File(procedimento.getRelatorio().getCaminhoPdf());
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir documento: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

}
