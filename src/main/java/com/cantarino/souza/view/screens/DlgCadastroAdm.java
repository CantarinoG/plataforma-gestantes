package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.AdminController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.exceptions.UsuarioException;
import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class DlgCadastroAdm extends JDialog {
    JPanel panFundo;
    JPanel panColuna;
    JLabel lblTitulo;
    JTextField edtNome;
    JFormattedTextField edtCPF;
    JPanel panCampoNome;
    JPasswordField edtSenha;
    JPanel panCampoSenha;
    JPanel panBotao;
    JButton btnCriarConta;
    JPanel panCampoData;
    JTextField edtEmail;
    JFormattedTextField edtDataNascimento;
    JFormattedTextField edtTelefone;
    JTextField edtEndereco;
    JPanel panCampoCpf;
    JPanel panCampoEmail;
    JPanel panCampoTelefone;
    JPanel panCampoEndereco;
    JPasswordField edtConfSenha;
    JPanel panCampoConfSenha;

    private AdminController adminController;
    private Admin atualizando = null;

    public DlgCadastroAdm(JDialog parent, boolean modal, int id) { // Construtor com id para edição
        super(parent, modal);

        adminController = new AdminController();
        atualizando = adminController.buscar(id);

        initComponents();

        edtCPF.setText(atualizando.getCpf());
        edtNome.setText(atualizando.getNome());
        edtEmail.setText(atualizando.getEmail());
        edtDataNascimento.setText(atualizando.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        edtTelefone.setText(atualizando.getTelefone());
        edtEndereco.setText(atualizando.getEndereco());
    }

    public DlgCadastroAdm(JDialog parent, boolean modal) { // Construtor sem id para criação
        super(parent, modal);

        adminController = new AdminController();

        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Gestante");
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

        lblTitulo = new JLabel(atualizando != null ? "Editar Administrador(a)" : "Cadastrar Administrador(a)");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(6, 2, 20, 10));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna, gbc);

        edtCPF = new JFormattedTextField();
        edtCPF.setFont(new Font("Arial", Font.PLAIN, 22));
        edtCPF.setBackground(AppColors.FIELD_PINK);
        edtCPF.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoCpf = criarTextFieldCustomizado("CPF", edtCPF);
        panColuna.add(panCampoCpf);

        edtNome = new JTextField();
        edtNome.setFont(new Font("Arial", Font.PLAIN, 22));
        edtNome.setBackground(AppColors.FIELD_PINK);
        edtNome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoNome = criarTextFieldCustomizado("Nome", edtNome);
        panColuna.add(panCampoNome);

        edtEmail = new JTextField();
        edtEmail.setFont(new Font("Arial", Font.PLAIN, 22));
        edtEmail.setBackground(AppColors.FIELD_PINK);
        edtEmail.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoEmail = criarTextFieldCustomizado("Email", edtEmail);
        panColuna.add(panCampoEmail);

        if (atualizando == null) {
            edtSenha = new JPasswordField();
            edtSenha.setFont(new Font("Arial", Font.PLAIN, 22));
            edtSenha.setBackground(AppColors.FIELD_PINK);
            edtSenha.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panCampoSenha = criarTextFieldCustomizado("Senha", edtSenha);
            panColuna.add(panCampoSenha);

            edtConfSenha = new JPasswordField();
            edtConfSenha.setFont(new Font("Arial", Font.PLAIN, 22));
            edtConfSenha.setBackground(AppColors.FIELD_PINK);
            edtConfSenha.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panCampoConfSenha = criarTextFieldCustomizado("Confirmar Senha", edtConfSenha);
            panColuna.add(panCampoConfSenha);
        }

        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');

            edtDataNascimento = new JFormattedTextField(maskData);
            edtDataNascimento.setFont(new Font("Arial", Font.PLAIN, 22));
            edtDataNascimento.setBackground(AppColors.FIELD_PINK);
            edtDataNascimento.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            edtDataNascimento.setToolTipText("Digite a data no formato: dd/mm/aaaa");

            panCampoData = criarTextFieldCustomizado("Data de Nascimento", edtDataNascimento);
            panColuna.add(panCampoData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edtTelefone = new JFormattedTextField();
        edtTelefone.setFont(new Font("Arial", Font.PLAIN, 22));
        edtTelefone.setBackground(AppColors.FIELD_PINK);
        edtTelefone.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoTelefone = criarTextFieldCustomizado("Telefone", edtTelefone);
        panColuna.add(panCampoTelefone);

        edtEndereco = new JTextField();
        edtEndereco.setFont(new Font("Arial", Font.PLAIN, 22));
        edtEndereco.setBackground(AppColors.FIELD_PINK);
        edtEndereco.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoEndereco = criarTextFieldCustomizado("Endereço", edtEndereco);
        panColuna.add(panCampoEndereco);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 1.0;
        gbcButton.anchor = GridBagConstraints.CENTER;
        gbcButton.insets = new java.awt.Insets(20, 0, 0, 0);

        panBotao = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panBotao.setBackground(AppColors.TRANSPARENT);

        btnCriarConta = new RoundedButton(atualizando != null ? "Editar Conta" : "Cadastrar Conta", 10);
        btnCriarConta.setPreferredSize(new Dimension(150, 50));
        btnCriarConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCriarConta.setForeground(Color.WHITE);
        btnCriarConta.addActionListener(evt -> btnCriarContaActionPerformed(evt));

        panBotao.add(btnCriarConta);

        panFundo.add(panBotao, gbcButton);

        try {
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.install(edtCPF);
        } catch (Exception e) {
        }
    }

    private JPanel criarTextFieldCustomizado(String hint, JComponent textField) {
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

            if (atualizando == null) { // Criando novo adm

                adminController.salvar(
                        cpf,
                        edtNome.getText(),
                        edtEmail.getText(),
                        new String(edtSenha.getPassword()),
                        new String(edtConfSenha.getPassword()),
                        dataNascimento,
                        edtTelefone.getText(),
                        edtEndereco.getText(),
                        null);
                dispose();
            } else { // Atualizando adm
                adminController.editar(
                        atualizando.getId(),
                        cpf,
                        edtNome.getText(),
                        edtEmail.getText(),
                        atualizando.getSenha(),
                        dataNascimento,
                        edtTelefone.getText(),
                        edtEndereco.getText(),
                        null);
                dispose();
            }

        } catch (UsuarioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
