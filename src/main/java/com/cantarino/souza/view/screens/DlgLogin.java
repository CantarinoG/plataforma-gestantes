package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
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

    public DlgLogin(JFrame parent, boolean modal) {
        super(parent, modal);
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
        panColumn.setLayout(new GridLayout(5, 1, 0, 20));
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

        String[] userTypes = { "Gestante", "Médico(a)", "Secretário(a)", "Administrador(a)" };
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
        System.out.println("Clicou no botão de Login!");
    }

    private void cmbUserTypeActionPerformed(java.awt.event.ActionEvent evt) {
        panBackground.repaint();
        // String selectedType = (String) cmbUserType.getSelectedItem();
        // System.out.println("Selected user type: " + selectedType);

    }

}
