package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import com.cantarino.souza.view.components.*;

public class DlgLogin extends JDialog {

    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JPanel panColumn;
    JLabel lblAction;
    JTextField edtLogin;
    JPanel panLoginField;
    JPasswordField edtPass;
    JPanel panPassField;
    JPanel panButton;
    JButton btnLogin;

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

        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(4, 1, 0, 20));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panContent.add(panColumn);

        lblAction = new JLabel("Login");
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panColumn.add(lblAction);

        edtLogin = new JTextField();
        edtLogin.setFont(new Font("Arial", Font.PLAIN, 22));
        edtLogin.setBackground(AppColors.FIELD_PINK);
        edtLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panLoginField = createCustomTextfield("CPF", edtLogin);
        panColumn.add(panLoginField);

        edtPass = new JPasswordField();
        edtPass.setFont(new Font("Arial", Font.PLAIN, 22));
        edtPass.setBackground(AppColors.FIELD_PINK);
        edtPass.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panPassField = createCustomTextfield("Senha", edtPass);
        panColumn.add(panPassField);

        panButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panButton.setBackground(AppColors.TRANSPARENT);
        btnLogin = new RoundedButton("Fazer Login", 50);
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

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de Login!");
    }

}
