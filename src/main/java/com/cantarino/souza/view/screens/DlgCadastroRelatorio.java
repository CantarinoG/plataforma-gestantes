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
    private JPanel panBackground;
    private JPanel panColumn;
    private JLabel lblAction;
    private JPanel panButton;
    private JButton btnCadastrarRelatorio;
    private JTextArea edtObservacoes;
    private JTextField edtResultado;
    private JPanel panDocumentoPdfContent;
    private JTextField edtArquivoSelecionado;
    private JButton btnSelecionarArquivo;
    private JPanel panDocumentoPdfField;
    private JPanel panObservacoesField;
    private JPanel panResultadoField;

    private RelatorioController relatorioController;
    private ConsultaController consultaController;
    private ExameController exameController;

    private Relatorio atualizando = null;

    private int procedimentoId;
    private String selectedFilePath;

    public DlgCadastroRelatorio(JFrame parent, boolean modal, int procedimentoId) {
        super(parent, modal);
        relatorioController = new RelatorioController();
        consultaController = new ConsultaController();
        exameController = new ExameController();
        this.procedimentoId = procedimentoId;
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Relatório");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
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

        lblAction = new JLabel("Cadastrar Relatório");
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

        edtResultado = new JTextField();
        edtResultado.setFont(new Font("Arial", Font.PLAIN, 22));
        edtResultado.setBackground(AppColors.FIELD_PINK);
        edtResultado.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panResultadoField = createCustomTextfield("Resultado", edtResultado);
        panColumn.add(panResultadoField);

        panDocumentoPdfContent = new JPanel(new BorderLayout(10, 0));
        panDocumentoPdfContent.setBackground(AppColors.FIELD_PINK);

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

        panDocumentoPdfContent.add(edtArquivoSelecionado, BorderLayout.CENTER);
        panDocumentoPdfContent.add(btnSelecionarArquivo, BorderLayout.EAST);

        panDocumentoPdfField = createCustomTextfield("Documento PDF (Opcional)", panDocumentoPdfContent);
        panColumn.add(panDocumentoPdfField);

        edtObservacoes = new JTextArea();
        edtObservacoes.setFont(new Font("Arial", Font.PLAIN, 22));
        edtObservacoes.setBackground(AppColors.FIELD_PINK);
        edtObservacoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        edtObservacoes.setLineWrap(true);
        edtObservacoes.setWrapStyleWord(true);
        panObservacoesField = createCustomTextfield("Observações (Opcional)", edtObservacoes);

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

        btnCadastrarRelatorio = new RoundedButton("Cadastrar Relatório", 10);
        btnCadastrarRelatorio.setPreferredSize(new Dimension(150, 50));
        btnCadastrarRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarRelatorio.setForeground(Color.WHITE);
        btnCadastrarRelatorio.addActionListener(evt -> btnCadastrarRelatorioActionPerformed(evt));

        panButton.add(btnCadastrarRelatorio);
        panBackground.add(panButton, gbcButton);
    }

    private JPanel createCustomTextfield(String hint, JComponent textField) {
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
            String destinationPath = null;

            if (selectedFilePath != null && !selectedFilePath.isEmpty()) {
                File sourceFile = new File(selectedFilePath);
                String fileName = "relatorio_" + procedimentoId + ".pdf";
                destinationPath = "storage/relatorios/" + fileName;
                File destinationFile = new File(destinationPath);

                destinationFile.getParentFile().mkdirs();

                try {
                    Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    destinationPath = null;
                    JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo PDF", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (atualizando == null) { // Criando
                int relatorioId = relatorioController.cadastrar(LocalDateTime.now(), resultado, observacoes,
                        destinationPath, null);
                Relatorio relatorioCriado = relatorioController.buscarPorId(relatorioId);

                Procedimento procedimento = consultaController.buscarPorId(procedimentoId);

                if (procedimento != null) { // Pertence a uma consulta
                    consultaController.adicionarRelatorio(procedimento.getId(), relatorioCriado);
                } else { // Pertence a um exame
                    procedimento = exameController.buscarPorId(procedimentoId);
                }

                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnSelecionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar arquivo PDF");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos PDF (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            edtArquivoSelecionado.setText(selectedFile.getName());
            selectedFilePath = selectedFile.getAbsolutePath();
        }
    }
}
