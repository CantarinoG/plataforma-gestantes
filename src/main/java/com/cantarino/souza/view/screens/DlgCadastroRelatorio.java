package com.cantarino.souza.view.screens;

import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class DlgCadastroRelatorio extends JDialog {
    private JPanel panBackground;
    private JPanel panColumn;
    private JLabel lblAction;
    private JPanel panButton;
    private JButton btnCadastrarRelatorio;
    private JFormattedTextField txtDataEmissao;
    private JTextField txtProcedimento;
    private JTextArea txtObservacoes;
    private JPanel panDataEmissaoField;
    private JPanel panProcedimentoField;
    private JPanel panResultadoContent;
    private JTextField txtArquivoSelecionado;
    private JButton btnSelecionarArquivo;
    private JPanel panResultadoField;
    private JPanel panObservacoesField;

    public DlgCadastroRelatorio(JFrame parent, boolean modal) {
        super(parent, modal);
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

        // Título
        lblAction = new JLabel("Cadastrar Relatório");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        // Configuração para o painel de campos
        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(30, 0, 10, 0);
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(4, 1, 20, 5));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn, gbc);

        // Campo Data de Emissão
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            txtDataEmissao = new JFormattedTextField(maskData);
            txtDataEmissao.setFont(new Font("Arial", Font.PLAIN, 22));
            txtDataEmissao.setBackground(AppColors.FIELD_PINK);
            txtDataEmissao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panDataEmissaoField = createCustomTextfield("Data de Emissão", txtDataEmissao);
            panColumn.add(panDataEmissaoField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Campo Procedimento
        txtProcedimento = new JTextField();
        txtProcedimento.setFont(new Font("Arial", Font.PLAIN, 22));
        txtProcedimento.setBackground(AppColors.FIELD_PINK);
        txtProcedimento.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panProcedimentoField = createCustomTextfield("Procedimento", txtProcedimento);
        panColumn.add(panProcedimentoField);

        // Campo Resultado
        panResultadoContent = new JPanel(new BorderLayout(10, 0));
        panResultadoContent.setBackground(AppColors.FIELD_PINK);

        txtArquivoSelecionado = new JTextField("Nenhum arquivo selecionado");
        txtArquivoSelecionado.setEditable(false);
        txtArquivoSelecionado.setBackground(AppColors.FIELD_PINK);
        txtArquivoSelecionado.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        txtArquivoSelecionado.setFont(new Font("Arial", Font.PLAIN, 14));

        btnSelecionarArquivo = new JButton("Selecionar Arquivo");
        btnSelecionarArquivo.setBackground(AppColors.BUTTON_PINK);
        btnSelecionarArquivo.setFont(new Font("Arial", Font.BOLD, 12));
        btnSelecionarArquivo.setFocusPainted(false);
        btnSelecionarArquivo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSelecionarArquivo.setPreferredSize(new Dimension(130, 30));

        panResultadoContent.add(txtArquivoSelecionado, BorderLayout.CENTER);
        panResultadoContent.add(btnSelecionarArquivo, BorderLayout.EAST);

        panResultadoField = createCustomTextfield("Resultado (PDF, DOC, DOCX)", panResultadoContent);
        panColumn.add(panResultadoField);

        // Campo Observações
        txtObservacoes = new JTextArea();
        txtObservacoes.setFont(new Font("Arial", Font.PLAIN, 22));
        txtObservacoes.setBackground(AppColors.FIELD_PINK);
        txtObservacoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);
        panObservacoesField = createCustomTextfield("Observações", txtObservacoes);

        panColumn.add(panObservacoesField);

        // Botão
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
        System.out.println("Clicou no botão de Cadastrar Relatório!");
    }
}
