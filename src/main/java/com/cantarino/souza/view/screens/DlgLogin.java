package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.view.components.*;

public class DlgLogin extends JDialog {

    JPanel panBackground;
    JPanel panColumn;
    JLabel lblAction;
    JFormattedTextField edtLogin;
    JPanel panLoginField;
    JPasswordField edtPass;
    JPanel panPassField;
    JPanel panUserType;
    JPanel panButton;
    JButton btnLogin;
    JComboBox<String> cmbUserType;
    JLabel lblForgotPassword;
    JPanel panForgotPassword;

    private final String GESTANTE = "Gestante";
    private final String MEDICO = "Médico(a)";
    private final String SECRETARIO = "Secretário(a)";
    private final String ADMIN = "Administrador(a)";

    private AutenticacaoController autenticacaoController;

    public DlgLogin(JFrame parent, boolean modal) {
        super(parent, modal);
        autenticacaoController = new AutenticacaoController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Autenticação");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new GridBagLayout());
        setContentPane(panBackground);

        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(6, 1, 0, 20));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn);

        lblAction = new JLabel("Login");
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panColumn.add(lblAction);

        edtLogin = new JFormattedTextField();
        edtLogin.setFont(new Font("Arial", Font.PLAIN, 22));
        edtLogin.setBackground(AppColors.FIELD_PINK);
        edtLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            edtLogin.setFormatterFactory(new DefaultFormatterFactory(cpfMask));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        panLoginField = createCustomTextfield("CPF", edtLogin);
        panColumn.add(panLoginField);

        edtPass = new JPasswordField();
        edtPass.setFont(new Font("Arial", Font.PLAIN, 22));
        edtPass.setBackground(AppColors.FIELD_PINK);
        edtPass.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panPassField = createCustomTextfield("Senha", edtPass);
        panColumn.add(panPassField);

        String[] userTypes = { GESTANTE, MEDICO, SECRETARIO, ADMIN };
        cmbUserType = new JComboBox<>(userTypes);
        cmbUserType.setFont(new Font("Arial", Font.PLAIN, 22));
        cmbUserType.setBackground(AppColors.FIELD_PINK);
        cmbUserType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUserTypeActionPerformed(evt);
            }
        });
        panUserType = createCustomTextfield("Tipo de Usuário", cmbUserType);
        panColumn.add(panUserType);

        // Forgot Password Label
        panForgotPassword = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panForgotPassword.setBackground(AppColors.TRANSPARENT);
        lblForgotPassword = new JLabel("Esqueci minha senha");
        lblForgotPassword.setFont(new Font("Arial", Font.BOLD, 16));
        lblForgotPassword.setForeground(AppColors.BUTTON_PINK);
        lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panForgotPassword.add(lblForgotPassword);
        panColumn.add(panForgotPassword);
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgotPasswordActionPerformed(evt);
            }
        });

        panButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panButton.setBackground(AppColors.TRANSPARENT);
        btnLogin = new RoundedButton("Fazer Login", 10);
        btnLogin.setPreferredSize(new Dimension(150, 50));
        btnLogin.setForeground(Color.white);
        btnLogin.setBackground(AppColors.BUTTON_PINK);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setPreferredSize(new Dimension(200, 55));
        btnLogin.setOpaque(true);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        panButton.add(btnLogin);
        panColumn.add(panButton);
    }

    private JPanel createCustomTextfield(String hint, JComponent textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        fieldPanel.setPreferredSize(new Dimension(626, 80));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel loginHintLabel = new JLabel(hint);
        loginHintLabel.setFont(new Font("Arial", Font.BOLD, 22));
        fieldPanel.add(loginHintLabel, BorderLayout.WEST);

        if (textField instanceof JTextField) {
            ((JTextField) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        } else if (textField instanceof JComboBox) {
            ((JComboBox<?>) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        }

        textField.setBackground(AppColors.FIELD_PINK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedType = (String) cmbUserType.getSelectedItem();
        String login = edtLogin.getText().replaceAll("[.-]", "");
        String senha = new String(edtPass.getPassword());

        try {
            switch (selectedType) {
                case GESTANTE:
                    autenticacaoController.logInGestante(login, senha);
                    break;
                case MEDICO:
                    autenticacaoController.logInMedico(login, senha);
                    break;
                case SECRETARIO:
                    autenticacaoController.logInSecretario(login, senha);
                    break;
                case ADMIN:
                    autenticacaoController.logInAdmin(login, senha);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Selecione um tipo de usuário válido");
                    return;
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cmbUserTypeActionPerformed(java.awt.event.ActionEvent evt) {
        panBackground.repaint();
    }

    private void lblForgotPasswordActionPerformed(java.awt.event.MouseEvent evt) {
        System.out.println("Esqueceu?");
    }

}
