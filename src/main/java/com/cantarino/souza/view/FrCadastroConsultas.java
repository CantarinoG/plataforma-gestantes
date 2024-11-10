package com.cantarino.souza.view;

import com.cantarino.souza.components.*;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class FrCadastroConsultas extends JFrame {
    private JPanel panBackground;
    private JPanel panHeader;
    private JLabel lblTitle;
    private JPanel panContent;
    private JPanel panColumn;
    private JLabel lblAction;
    private JPanel panButton;
    private JButton btnCadastrarConsulta;
    private JTextField txtPaciente;
    private JTextArea txtDescricao;
    private JFormattedTextField txtData;
    private JTextField txtValor;
    private JTextField txtMedico;
    private JFormattedTextField txtDataRetorno;
    private JPanel panPacienteField;
    private JPanel panDescricaoField;
    private JPanel panDataField;
    private JPanel panValorField;
    private JPanel panMedicoField;
    private JPanel panDataRetornoField;
    private JPanel panResultadoContent;
    private JTextField txtArquivoSelecionado;
    private JButton btnSelecionarArquivo;
    private JPanel panResultadoField;

    public FrCadastroConsultas() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Cadastrar Consulta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new BorderLayout());
        setContentPane(panBackground);

        panHeader = new JPanel();
        panHeader.setLayout(new BorderLayout());
        panHeader.setBackground(AppColors.DARKER);
        panHeader.setPreferredSize(new Dimension(getWidth(), 74));
        panHeader.setBorder(BorderFactory.createEmptyBorder(15, 64, 15, 64));
        panBackground.add(panHeader, BorderLayout.NORTH);

        lblTitle = new JLabel("BemGestar");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        panHeader.add(lblTitle, BorderLayout.WEST);

        panContent = new JPanel();
        panContent.setLayout(new GridBagLayout());
        panContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panContent.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panContent, BorderLayout.CENTER);

        // Configuração do GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        // Título
        lblAction = new JLabel("Cadastrar Consulta");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panContent.add(lblAction, gbc);

        // Configuração para o painel de campos
        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(30, 0, 10, 0);
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(7, 1, 20, 5));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panContent.add(panColumn, gbc);

        // Campo Paciente
        txtPaciente = new JTextField();
        txtPaciente.setFont(new Font("Arial", Font.PLAIN, 22));
        txtPaciente.setBackground(AppColors.FIELD_PINK);
        txtPaciente.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panPacienteField = createCustomTextfield("Paciente", txtPaciente);
        panColumn.add(panPacienteField);

        // Campo Descrição da Consulta
        txtDescricao = new JTextArea();
        txtDescricao.setFont(new Font("Arial", Font.PLAIN, 22));
        txtDescricao.setBackground(AppColors.FIELD_PINK);
        txtDescricao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        panDescricaoField = createCustomTextfield("Descrição da Consulta", txtDescricao);
        panColumn.add(panDescricaoField);

        // Campo Data
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            txtData = new JFormattedTextField(maskData);
            txtData.setFont(new Font("Arial", Font.PLAIN, 22));
            txtData.setBackground(AppColors.FIELD_PINK);
            txtData.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panDataField = createCustomTextfield("Data", txtData);
            panColumn.add(panDataField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Campo Valor
        txtValor = new JTextField();
        txtValor.setFont(new Font("Arial", Font.PLAIN, 22));
        txtValor.setBackground(AppColors.FIELD_PINK);
        txtValor.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panValorField = createCustomTextfield("Valor", txtValor);
        panColumn.add(panValorField);

        // Campo Médico
        txtMedico = new JTextField();
        txtMedico.setFont(new Font("Arial", Font.PLAIN, 22));
        txtMedico.setBackground(AppColors.FIELD_PINK);
        txtMedico.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panMedicoField = createCustomTextfield("Médico", txtMedico);
        panColumn.add(panMedicoField);

        // Campo Data de Retorno
        try {
            MaskFormatter maskDataRetorno = new MaskFormatter("##/##/####");
            maskDataRetorno.setPlaceholderCharacter('_');
            txtDataRetorno = new JFormattedTextField(maskDataRetorno);
            txtDataRetorno.setFont(new Font("Arial", Font.PLAIN, 22));
            txtDataRetorno.setBackground(AppColors.FIELD_PINK);
            txtDataRetorno.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panDataRetornoField = createCustomTextfield("Data de Retorno", txtDataRetorno);
            panColumn.add(panDataRetornoField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

        btnCadastrarConsulta = new RoundedButton("Cadastrar Consulta", 50);
        btnCadastrarConsulta.setBackground(AppColors.BUTTON_PINK);
        btnCadastrarConsulta.setFont(new Font("Arial", Font.BOLD, 15));
        btnCadastrarConsulta.setFocusPainted(false);
        btnCadastrarConsulta.setBorderPainted(false);
        btnCadastrarConsulta.setPreferredSize(new Dimension(200, 55));
        btnCadastrarConsulta.setOpaque(true);
        btnCadastrarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarConsulta.addActionListener(evt -> btnCadastrarConsultaActionPerformed(evt));

        panButton.add(btnCadastrarConsulta);
        panContent.add(panButton, gbcButton);
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

    private void btnCadastrarConsultaActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de Cadastrar Consulta!");
    }
}
