package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.model.exceptions.UsuarioException;
import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePickerImpl;

public class DlgCadastroGestantes extends JDialog {
    JPanel panBackground;
    JPanel panColumn;
    JLabel lblAction;
    JTextField edtNome;
    JFormattedTextField edtCPF;
    JPanel panNomeField;
    JPasswordField edtPass;
    JPanel panPassField;
    JPanel panButton;
    JButton btnCriarConta;
    JDatePickerImpl datePicker;
    JPanel panDateField;
    JTextField edtEmail;
    JFormattedTextField edtDataNascimento;
    JFormattedTextField edtTelefone;
    JTextField edtEndereco;
    JFormattedTextField edtPrevisaoParto;
    JTextField edtContatoEmergencia;
    JComboBox<String> edtTipoSanguineo;
    JTextArea edtHistoricoMedico;
    JPanel panCPFField;
    JPanel panEmailField;
    JPanel panTelefoneField;
    JPanel panEnderecoField;
    JPanel panPrevisaoPartoField;
    JPanel panContatoEmergenciaField;
    JPanel panTipoSanguineoField;
    JPanel panHistoricoMedicoField;

    private GestanteController gestanteController;

    public DlgCadastroGestantes(JFrame parent, boolean modal) {
        super(parent, modal);
        gestanteController = new GestanteController();
        initComponents();
    }

    public DlgCadastroGestantes(JDialog parent, boolean modal) {
        super(parent, modal);
        gestanteController = new GestanteController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Gestante");
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

        // Adiciona o título
        lblAction = new JLabel("Cadastrar Gestante");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        // Configuração para o painel de campos
        gbc.gridy = 1; // Próxima linha
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

        // Campo para Nome
        edtNome = new JTextField();
        edtNome.setFont(new Font("Arial", Font.PLAIN, 22));
        edtNome.setBackground(AppColors.FIELD_PINK);
        edtNome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panNomeField = createCustomTextfield("Nome", edtNome);
        panColumn.add(panNomeField);

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

        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');

            edtPrevisaoParto = new JFormattedTextField(maskData);
            edtPrevisaoParto.setFont(new Font("Arial", Font.PLAIN, 22));
            edtPrevisaoParto.setBackground(AppColors.FIELD_PINK);
            edtPrevisaoParto.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            edtPrevisaoParto.setToolTipText("Digite a data no formato: dd/mm/aaaa");

            panPrevisaoPartoField = createCustomTextfield("Previsão de Parto", edtPrevisaoParto);
            panColumn.add(panPrevisaoPartoField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Campo para Contato de Emergência
        edtContatoEmergencia = new JTextField();
        edtContatoEmergencia.setFont(new Font("Arial", Font.PLAIN, 22));
        edtContatoEmergencia.setBackground(AppColors.FIELD_PINK);
        edtContatoEmergencia.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panContatoEmergenciaField = createCustomTextfield("Contato de Emergência", edtContatoEmergencia);
        panColumn.add(panContatoEmergenciaField);

        String[] tiposSanguineos = {
                "A+", "A-",
                "B+", "B-",
                "AB+", "AB-",
                "O+", "O-"
        };

        edtTipoSanguineo = new JComboBox<>(tiposSanguineos);
        edtTipoSanguineo.setFont(new Font("Arial", Font.PLAIN, 22));
        edtTipoSanguineo.setBackground(AppColors.FIELD_PINK);
        edtTipoSanguineo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        edtTipoSanguineo.setEditable(false);

        panTipoSanguineoField = createCustomTextfield("Tipo Sanguíneo", edtTipoSanguineo);
        panColumn.add(panTipoSanguineoField);

        // Campo para Histórico Médico
        edtHistoricoMedico = new JTextArea();
        edtHistoricoMedico.setFont(new Font("Arial", Font.PLAIN, 22));
        edtHistoricoMedico.setBackground(AppColors.FIELD_PINK);
        edtHistoricoMedico.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panHistoricoMedicoField = createCustomTextfield("Histórico Médico(Opcional)", edtHistoricoMedico);
        panColumn.add(panHistoricoMedicoField);

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

            String previsaoParto = edtPrevisaoParto.getText().split("/")[2] + "-" +
                    edtPrevisaoParto.getText().split("/")[1] + "-" +
                    edtPrevisaoParto.getText().split("/")[0];

            gestanteController.cadastrar(
                    cpf,
                    edtNome.getText(),
                    edtEmail.getText(),
                    new String(edtPass.getPassword()),
                    dataNascimento,
                    edtTelefone.getText(),
                    edtEndereco.getText(),
                    null,
                    previsaoParto,
                    edtContatoEmergencia.getText(),
                    edtHistoricoMedico.getText(),
                    (String) edtTipoSanguineo.getSelectedItem());
            dispose();
        } catch (UsuarioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
