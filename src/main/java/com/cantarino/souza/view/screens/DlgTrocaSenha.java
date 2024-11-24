package com.cantarino.souza.view.screens;

import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class DlgTrocaSenha extends JDialog {
    JPanel panBackground;
    JPanel panColumn;
    JPasswordField edtSenha;
    JPasswordField edtConfirmarSenha;
    JPanel panButton;
    RoundedButton btnTrocarSenha;
    JLabel lblHint;
    JPanel panField;

    public DlgTrocaSenha(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setTitle("Troca de Senha");
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
        gbc.insets = new Insets(10, 0, 10, 0);

        edtSenha = new JPasswordField();
        configurarCampoSenha(edtSenha);
        panBackground.add(createCustomTextfield("Nova Senha", edtSenha), gbc);

        gbc.gridy++;
        edtConfirmarSenha = new JPasswordField();
        configurarCampoSenha(edtConfirmarSenha);
        panBackground.add(createCustomTextfield("Confirmar Nova Senha", edtConfirmarSenha), gbc);

        gbc.gridy++;
        btnTrocarSenha = new RoundedButton("Trocar Senha", 10);
        btnTrocarSenha.setPreferredSize(new Dimension(150, 50));
        btnTrocarSenha.setForeground(Color.WHITE);
        btnTrocarSenha.setBackground(AppColors.BUTTON_PINK);
        btnTrocarSenha.setFont(new Font("Arial", Font.BOLD, 15));
        btnTrocarSenha.setFocusPainted(false);
        btnTrocarSenha.setBorderPainted(false);
        btnTrocarSenha.setPreferredSize(new Dimension(200, 40));
        btnTrocarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTrocarSenha.addActionListener(evt -> btnTrocarSenhaActionPerformed(evt));
        panBackground.add(btnTrocarSenha, gbc);
    }

    private void configurarCampoSenha(JPasswordField campo) {
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBackground(AppColors.FIELD_PINK);
        campo.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    private JPanel createCustomTextfield(String hint, JComponent textField) {
        panField = new JPanel();
        panField.setLayout(new BorderLayout());
        panField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        panField.setPreferredSize(new Dimension(400, 50));
        panField.setBackground(AppColors.FIELD_PINK);

        lblHint = new JLabel(hint);
        lblHint.setFont(new Font("Arial", Font.BOLD, 12));
        panField.add(lblHint, BorderLayout.NORTH);

        panField.add(textField, BorderLayout.CENTER);

        return panField;
    }

    private void btnTrocarSenhaActionPerformed(ActionEvent evt) {
        String senha = new String(edtSenha.getPassword());
        String confirmarSenha = new String(edtConfirmarSenha.getPassword());

        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this,
                    "As senhas não conferem. Por favor, digite novamente.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }
}
