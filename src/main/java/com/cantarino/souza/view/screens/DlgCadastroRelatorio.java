package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.controller.ExameController;
import com.cantarino.souza.controller.RelatorioController;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DlgCadastroRelatorio extends JDialog {
    private JPanel panFundo;
    private JPanel panColuna;
    private JLabel lblTitulo;
    private JPanel panBotao;
    private JButton btnCadastrarRelatorio;
    private JTextArea edtObservacoes;
    private JTextField edtResultado;
    private JPanel panDocumentoPdf;
    private JTextField edtArquivoSelecionado;
    private JButton btnSelecionarArquivo;
    private JPanel panCampoDocumentoPdf;
    private JPanel panObservacoes;
    private JPanel panResultado;

    private RelatorioController relatorioController;
    private ConsultaController consultaController;
    private ExameController exameController;

    private Relatorio atualizando = null;

    private int procedimentoId;
    private String selectedFilePath;

    public DlgCadastroRelatorio(JDialog parent, boolean modal, int procedimentoId) {
        super(parent, modal);

        relatorioController = new RelatorioController();
        consultaController = new ConsultaController();
        exameController = new ExameController();
        this.procedimentoId = procedimentoId;
        initComponents();

        Procedimento procedimento = consultaController.buscar(procedimentoId);
        if (procedimento == null) {
            procedimento = exameController.buscar(procedimentoId);
        }
        if (procedimento != null) {
            if (procedimento.getRelatorio() != null) {
                atualizando = procedimento.getRelatorio();
                edtResultado.setText(atualizando.getResultado());
                edtObservacoes.setText(atualizando.getObeservacoes());
                if (atualizando.getCaminhoPdf() != null) {
                    edtArquivoSelecionado.setText(atualizando.getCaminhoPdf());
                    selectedFilePath = atualizando.getCaminhoPdf();
                }
            }

        }
    }

    private void initComponents() {
        setTitle(atualizando == null ? "Cadastro de Relatório" : "Atualização de Relatório");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panFundo = new BackgroundPanel("/images/background.png");
        panFundo.setLayout(new GridBagLayout());
        setContentPane(panFundo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        lblTitulo = new JLabel(atualizando == null ? "Cadastrar Relatório" : "Atualizar Relatório");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(30, 0, 10, 0);
        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(3, 1, 20, 5));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna, gbc);

        edtResultado = new JTextField();
        edtResultado.setFont(new Font("Arial", Font.PLAIN, 22));
        edtResultado.setBackground(AppColors.FIELD_PINK);
        edtResultado.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panResultado = criarTextFieldCustomizado("Resultado", edtResultado);
        panColuna.add(panResultado);

        panDocumentoPdf = new JPanel(new BorderLayout(10, 0));
        panDocumentoPdf.setBackground(AppColors.FIELD_PINK);

        edtArquivoSelecionado = new JTextField("Nenhum arquivo selecionado");
        edtArquivoSelecionado.setEditable(false);
        edtArquivoSelecionado.setBackground(AppColors.FIELD_PINK);
        edtArquivoSelecionado.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        edtArquivoSelecionado.setFont(new Font("Arial", Font.PLAIN, 14));

        btnSelecionarArquivo = new JButton("Selecionar PDF");
        btnSelecionarArquivo.setBackground(AppColors.BUTTON_PINK);
        btnSelecionarArquivo.setFont(new Font("Arial", Font.BOLD, 12));
        btnSelecionarArquivo.setFocusPainted(false);
        btnSelecionarArquivo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSelecionarArquivo.setPreferredSize(new Dimension(130, 30));
        btnSelecionarArquivo.addActionListener(evt -> btnSelecionarArquivoActionPerformed(evt));

        panDocumentoPdf.add(edtArquivoSelecionado, BorderLayout.CENTER);
        panDocumentoPdf.add(btnSelecionarArquivo, BorderLayout.EAST);

        panCampoDocumentoPdf = criarTextFieldCustomizado("Documento PDF (Opcional)", panDocumentoPdf);
        panColuna.add(panCampoDocumentoPdf);

        edtObservacoes = new JTextArea();
        edtObservacoes.setFont(new Font("Arial", Font.PLAIN, 22));
        edtObservacoes.setBackground(AppColors.FIELD_PINK);
        edtObservacoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        edtObservacoes.setLineWrap(true);
        edtObservacoes.setWrapStyleWord(true);
        panObservacoes = criarTextFieldCustomizado("Observações (Opcional)", edtObservacoes);

        panColuna.add(panObservacoes);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 0.5;
        gbcButton.anchor = GridBagConstraints.NORTH;
        gbcButton.insets = new java.awt.Insets(10, 0, 0, 0);

        panBotao = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panBotao.setBackground(AppColors.TRANSPARENT);

        btnCadastrarRelatorio = new RoundedButton(atualizando == null ? "Cadastrar Relatório" : "Atualizar Relatório",
                10);
        btnCadastrarRelatorio.setPreferredSize(new Dimension(150, 50));
        btnCadastrarRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarRelatorio.setForeground(Color.WHITE);
        btnCadastrarRelatorio.addActionListener(evt -> btnCadastrarRelatorioActionPerformed(evt));

        panBotao.add(btnCadastrarRelatorio);
        panFundo.add(panBotao, gbcButton);
    }

    private JPanel criarTextFieldCustomizado(String hint, JComponent textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        fieldPanel.setPreferredSize(new Dimension(500, 60));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel hintLabel = new JLabel(hint);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldPanel.add(hintLabel, BorderLayout.NORTH);

        if (textField instanceof JTextField) {
            ((JTextField) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        } else if (textField instanceof JTextArea) {
            ((JTextArea) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        }

        textField.setBackground(AppColors.FIELD_PINK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void btnCadastrarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String resultado = edtResultado.getText();
            String observacoes = edtObservacoes.getText();
            String caminhoDestino = null;

            if (selectedFilePath != null && !selectedFilePath.isEmpty()) {
                File arquivoBase = new File(selectedFilePath);
                String nomeArquivo = "relatorio_" + procedimentoId + ".pdf";
                caminhoDestino = "storage/relatorios/" + nomeArquivo;
                File arquivoDestino = new File(caminhoDestino);

                arquivoDestino.getParentFile().mkdirs();

                try {
                    Files.copy(arquivoBase.toPath(), arquivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    caminhoDestino = null;
                    JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo PDF", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            int relatorioId = relatorioController.salvar(LocalDateTime.now(), resultado, observacoes,
                    caminhoDestino, null);
            Relatorio relatorioCriado = relatorioController.buscar(relatorioId);

            Procedimento procedimento = consultaController.buscar(procedimentoId);

            if (procedimento != null) { // Pertence a uma consulta
                consultaController.adicionarRelatorio(procedimento.getId(), procedimento.getPaciente(),
                        relatorioCriado);
            } else { // Pertence a um exame
                procedimento = exameController.buscar(procedimentoId);
                exameController.adicionarRelatorio(procedimento.getId(), procedimento.getPaciente(), relatorioCriado);
            }

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnSelecionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar arquivo PDF");

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos PDF (*.pdf)", "pdf");
        fileChooser.setFileFilter(filtro);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File arquivoEscolhido = fileChooser.getSelectedFile();
            edtArquivoSelecionado.setText(arquivoEscolhido.getName());
            selectedFilePath = arquivoEscolhido.getAbsolutePath();
        }
    }
}
