package com.cantarino.souza.view.screens;

import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class DlgCadastroMedicos extends JDialog {
    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JPanel panColumn;
    JLabel lblAction;
    JTextField edtNome;
    JFormattedTextField edtCPF;
    JFormattedTextField edtCRM;
    JPanel panNomeField;
    JPasswordField edtPass;
    JPanel panPassField;
    JPanel panButton;
    JButton btnCriarConta;
    JPanel panDateField;
    JPanel panCPFField;
    JPanel panCRMField;
    JComboBox<String> edtEspecializacao;
    JPanel panEspecializacaoField;
    JTextField edtEmail;
    JPanel panEmailField;
    JFormattedTextField edtDataNascimento;
    JFormattedTextField edtTelefone;
    JPanel panTelefoneField;
    JTextField edtEndereco;
    JPanel panEnderecoField;

    public DlgCadastroMedicos(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Médico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        lblAction = new JLabel("Cadastrar Médico");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panContent.add(lblAction, gbc);

        gbc.gridy = 1;
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(6, 2, 20, 10));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panContent.add(panColumn, gbc);

        // CPF
        edtCPF = new JFormattedTextField();
        edtCPF.setFont(new Font("Arial", Font.PLAIN, 22));
        edtCPF.setBackground(AppColors.FIELD_PINK);
        edtCPF.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCPFField = createCustomTextfield("CPF", edtCPF);
        panColumn.add(panCPFField);

        // CRM
        edtCRM = new JFormattedTextField();
        edtCRM.setFont(new Font("Arial", Font.PLAIN, 22));
        edtCRM.setBackground(AppColors.FIELD_PINK);
        edtCRM.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCRMField = createCustomTextfield("CRM", edtCRM);
        panColumn.add(panCRMField);

        // Campo para Nome
        edtNome = new JTextField();
        edtNome.setFont(new Font("Arial", Font.PLAIN, 22));
        edtNome.setBackground(AppColors.FIELD_PINK);
        edtNome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panNomeField = createCustomTextfield("Nome", edtNome);
        panColumn.add(panNomeField);

        // Especialização
        String[] especialidades = {
                "Selecione",
                "Obstetrícia",
                "Ginecologia",
                "Obstetrícia e Ginecologia",
                "Medicina Fetal",
                "Outro"
        };

        edtEspecializacao = new JComboBox<>(especialidades);
        edtEspecializacao.setFont(new Font("Arial", Font.PLAIN, 22));
        edtEspecializacao.setBackground(AppColors.FIELD_PINK);
        edtEspecializacao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        edtEspecializacao.setEditable(false);
        panEspecializacaoField = createCustomTextfield("Especialização", edtEspecializacao);
        panColumn.add(panEspecializacaoField);

        // Campo para Email
        edtEmail = new JTextField();
        edtEmail.setFont(new Font("Arial", Font.PLAIN, 22));
        edtEmail.setBackground(AppColors.FIELD_PINK);
        edtEmail.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEmailField = createCustomTextfield("Email", edtEmail);
        panColumn.add(panEmailField);

        edtPass = new JPasswordField();
        edtPass.setFont(new Font("Arial", Font.PLAIN, 22));
        edtPass.setBackground(AppColors.FIELD_PINK);
        edtPass.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panPassField = createCustomTextfield("Senha", edtPass);
        panColumn.add(panPassField);

        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');

            edtDataNascimento = new JFormattedTextField(maskData);
            edtDataNascimento.setFont(new Font("Arial", Font.PLAIN, 22));
            edtDataNascimento.setBackground(AppColors.FIELD_PINK);
            edtDataNascimento.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            edtDataNascimento.setToolTipText("Digite a data no formato: dd/mm/aaaa");

            panDateField = createCustomTextfield("Data de Nascimento", edtDataNascimento);
            panColumn.add(panDateField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Campo para Telefone
        edtTelefone = new JFormattedTextField();
        edtTelefone.setFont(new Font("Arial", Font.PLAIN, 22));
        edtTelefone.setBackground(AppColors.FIELD_PINK);
        edtTelefone.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panTelefoneField = createCustomTextfield("Telefone", edtTelefone);
        panColumn.add(panTelefoneField);

        // Campo para Endereço
        edtEndereco = new JTextField();
        edtEndereco.setFont(new Font("Arial", Font.PLAIN, 22));
        edtEndereco.setBackground(AppColors.FIELD_PINK);
        edtEndereco.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEnderecoField = createCustomTextfield("Endereço", edtEndereco);
        panColumn.add(panEnderecoField);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 1.0;
        gbcButton.anchor = GridBagConstraints.CENTER;
        gbcButton.insets = new java.awt.Insets(20, 0, 0, 0);

        panButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panButton.setBackground(AppColors.TRANSPARENT);

        btnCriarConta = new RoundedButton("Criar Conta", 50);
        btnCriarConta.setBackground(AppColors.BUTTON_PINK);
        btnCriarConta.setFont(new Font("Arial", Font.BOLD, 15));
        btnCriarConta.setFocusPainted(false);
        btnCriarConta.setBorderPainted(false);
        btnCriarConta.setPreferredSize(new Dimension(200, 55));
        btnCriarConta.setOpaque(true);
        btnCriarConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCriarConta.addActionListener(evt -> btnCriarContaActionPerformed(evt));

        panButton.add(btnCriarConta);

        panContent.add(panButton, gbcButton);

        try {
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            MaskFormatter maskCRM = new MaskFormatter("######-#");
            maskCPF.install(edtCPF);
            maskCRM.install(edtCRM);
        } catch (Exception e) {
        }
    }

    private JPanel createCustomTextfield(String hint, JComponent textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        fieldPanel.setPreferredSize(new Dimension(300, 60));
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

    private void btnCriarContaActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de Criar Conta!");
    }
}