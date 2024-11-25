package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.enums.EspecializacaoMedico;
import com.cantarino.souza.model.exceptions.SecretarioException;
import com.cantarino.souza.model.exceptions.UsuarioException;
import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class DlgCadastroMedicos extends JDialog {
    JPanel panBackground;
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
    JPasswordField edtConfirmPass;
    JPanel panConfirmPassField;

    private MedicoController medicoController;
    private Medico atualizando;

    public DlgCadastroMedicos(JDialog parent, boolean modal) {
        super(parent, modal);
        medicoController = new MedicoController();
        atualizando = null;
        initComponents();
    }

    public DlgCadastroMedicos(JDialog parent, boolean modal, int id) {
        super(parent, modal);
        medicoController = new MedicoController();
        atualizando = medicoController.buscarPorId(id);
        initComponents();

        edtCPF.setText(atualizando.getCpf());
        edtCRM.setText(atualizando.getCrm());
        edtNome.setText(atualizando.getNome());
        edtEmail.setText(atualizando.getEmail());
        edtDataNascimento.setText(atualizando.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        edtTelefone.setText(atualizando.getTelefone());
        edtEndereco.setText(atualizando.getEndereco());
        edtEspecializacao.setSelectedItem(atualizando.getEspecializacao());
    }

    private String[] getEspecializacoes() {
        EspecializacaoMedico[] values = EspecializacaoMedico.values();
        String[] options = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = values[i].getValue();
        }
        return options;
    }

    private void initComponents() {
        setTitle("Cadastro de Médico");
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

        lblAction = new JLabel(atualizando != null ? "Editar Médico(a)" : "Cadastrar Médico(a)");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        gbc.gridy = 1;
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(6, 2, 20, 10));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn, gbc);

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

        edtEspecializacao = new JComboBox<>(getEspecializacoes());
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

        if (atualizando == null) {
            edtPass = new JPasswordField();
            edtPass.setFont(new Font("Arial", Font.PLAIN, 22));
            edtPass.setBackground(AppColors.FIELD_PINK);
            edtPass.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panPassField = createCustomTextfield("Senha", edtPass);
            panColumn.add(panPassField);

            edtConfirmPass = new JPasswordField();
            edtConfirmPass.setFont(new Font("Arial", Font.PLAIN, 22));
            edtConfirmPass.setBackground(AppColors.FIELD_PINK);
            edtConfirmPass.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panConfirmPassField = createCustomTextfield("Confirmar Senha", edtConfirmPass);
            panColumn.add(panConfirmPassField);
        }

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

        btnCriarConta = new RoundedButton(atualizando != null ? "Editar Conta" : "Cadastrar Conta", 10);
        btnCriarConta.setPreferredSize(new Dimension(150, 50));
        btnCriarConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCriarConta.setForeground(Color.WHITE);
        btnCriarConta.addActionListener(evt -> btnCriarContaActionPerformed(evt));

        panButton.add(btnCriarConta);

        panBackground.add(panButton, gbcButton);

        try {
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.install(edtCPF);
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
        try {
            String cpf = edtCPF.getText().replaceAll("[^0-9]", "");

            String dataNascimento = edtDataNascimento.getText().split("/")[2] + "-" +
                    edtDataNascimento.getText().split("/")[1] + "-" +
                    edtDataNascimento.getText().split("/")[0];
            if (atualizando == null) {
                if (!(new String(edtPass.getPassword()).equals(new String(edtConfirmPass.getPassword())))) {
                    throw new SecretarioException("As senhas não coincidem.");
                }

                medicoController.cadastrar(
                        cpf,
                        edtNome.getText(),
                        edtEmail.getText(),
                        new String(edtPass.getPassword()),
                        dataNascimento,
                        edtTelefone.getText(),
                        edtEndereco.getText(),
                        null,
                        (String) edtEspecializacao.getSelectedItem(),
                        edtCRM.getText());
                dispose();
            } else {
                medicoController.atualizar(
                        atualizando.getId(),
                        cpf,
                        edtNome.getText(),
                        edtEmail.getText(),
                        atualizando.getSenha(),
                        dataNascimento,
                        edtTelefone.getText(),
                        edtEndereco.getText(),
                        null,
                        (String) edtEspecializacao.getSelectedItem(),
                        edtCRM.getText());
                dispose();
            }

        } catch (UsuarioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}