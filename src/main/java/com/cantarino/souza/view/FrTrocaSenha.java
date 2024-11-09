package com.cantarino.souza.view;
import com.cantarino.souza.components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;



public class FrTrocaSenha extends JFrame {
    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JPanel panColumn;
    JPasswordField edtSenhaAtual;
    JPasswordField edtNovaSenha;
    JPanel panButton;
    RoundedButton btnTrocarSenha;
  

    public FrTrocaSenha() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Trocar Senha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
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

        lblTitle = new JLabel("Bem Gestar");
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
        gbc.insets = new Insets(10, 0, 10, 0);


        JPasswordField edtSenhaAtual = new JPasswordField();
        configurarCampoSenha(edtSenhaAtual);
        panContent.add(createCustomTextfield("Senha Atual", edtSenhaAtual), gbc);

    
        gbc.gridy++;
        JPasswordField edtNovaSenha = new JPasswordField();
        configurarCampoSenha(edtNovaSenha);
        panContent.add(createCustomTextfield("Nova Senha", edtNovaSenha), gbc);


        gbc.gridy++;
        RoundedButton btnTrocarSenha = new RoundedButton("Trocar Senha", 50);
        btnTrocarSenha.setBackground(AppColors.BUTTON_PINK);
        btnTrocarSenha.setFont(new Font("Arial", Font.BOLD, 15));
        btnTrocarSenha.setFocusPainted(false);
        btnTrocarSenha.setBorderPainted(false);
        btnTrocarSenha.setPreferredSize(new Dimension(200, 40));
        btnTrocarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTrocarSenha.addActionListener(evt -> trocarSenhaActionPerformed(evt));
        panContent.add(btnTrocarSenha, gbc);
    }

    private void configurarCampoSenha(JPasswordField campo) {
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBackground(AppColors.FIELD_PINK);
        campo.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    private JPanel createCustomTextfield(String hint, JComponent textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        fieldPanel.setPreferredSize(new Dimension(400, 50));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel hintLabel = new JLabel(hint);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 12));
        fieldPanel.add(hintLabel, BorderLayout.NORTH);

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void trocarSenhaActionPerformed(ActionEvent evt) {
        System.out.println("Clicou em Trocar Senha");
    }
}
