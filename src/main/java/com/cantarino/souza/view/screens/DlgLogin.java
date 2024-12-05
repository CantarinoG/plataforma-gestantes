package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.cantarino.souza.controller.AdminController;
import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.controller.SecretarioController;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.TipoUsuario;
import com.cantarino.souza.view.components.*;

public class DlgLogin extends JDialog {

    JPanel panFundo;
    JPanel panColuna;
    JLabel lblTitulo;
    JFormattedTextField edtLogin;
    JPanel panLogin;
    JPasswordField edtSenha;
    JPanel panSenha;
    JPanel panTipoUsuario;
    JPanel panBotao;
    JButton btnLogin;
    JComboBox<String> cmbTipoUsuario;
    JLabel lblEsqueciSenha;
    JPanel panEsqueciSenha;

    private AutenticacaoController autenticacaoController;
    private GestanteController gestanteController;
    private MedicoController medicoController;
    private SecretarioController secretarioController;
    private AdminController adminController;

    public DlgLogin(JFrame parent, boolean modal) {
        super(parent, modal);

        autenticacaoController = new AutenticacaoController();
        gestanteController = new GestanteController();
        medicoController = new MedicoController();
        secretarioController = new SecretarioController();
        adminController = new AdminController();

        initComponents();
    }

    private void initComponents() {
        setTitle("Autenticação");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panFundo = new BackgroundPanel("/images/background.png");
        panFundo.setLayout(new GridBagLayout());
        setContentPane(panFundo);

        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(6, 1, 0, 20));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna);

        lblTitulo = new JLabel("Login");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 64));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panColuna.add(lblTitulo);

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

        panLogin = criarTextFieldCustomizado("CPF", edtLogin);
        panColuna.add(panLogin);

        edtSenha = new JPasswordField();
        edtSenha.setFont(new Font("Arial", Font.PLAIN, 22));
        edtSenha.setBackground(AppColors.FIELD_PINK);
        edtSenha.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panSenha = criarTextFieldCustomizado("Senha", edtSenha);
        panColuna.add(panSenha);

        String[] userTypes = { TipoUsuario.GESTANTE.getValor(), TipoUsuario.MEDICO.getValor(),
                TipoUsuario.SECRETARIO.getValor(), TipoUsuario.ADMIN.getValor() };
        cmbTipoUsuario = new JComboBox<>(userTypes);
        cmbTipoUsuario.setFont(new Font("Arial", Font.PLAIN, 22));
        cmbTipoUsuario.setBackground(AppColors.FIELD_PINK);
        cmbTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUserTypeActionPerformed(evt);
            }
        });
        panTipoUsuario = criarTextFieldCustomizado("Tipo de Usuário", cmbTipoUsuario);
        panColuna.add(panTipoUsuario);

        panEsqueciSenha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panEsqueciSenha.setBackground(AppColors.TRANSPARENT);
        lblEsqueciSenha = new JLabel("Esqueci minha senha");
        lblEsqueciSenha.setFont(new Font("Arial", Font.BOLD, 16));
        lblEsqueciSenha.setForeground(AppColors.BUTTON_PINK);
        lblEsqueciSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panEsqueciSenha.add(lblEsqueciSenha);
        panColuna.add(panEsqueciSenha);
        lblEsqueciSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgotPasswordActionPerformed(evt);
            }
        });

        panBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panBotao.setBackground(AppColors.TRANSPARENT);
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

        panBotao.add(btnLogin);
        panColuna.add(panBotao);
    }

    private JPanel criarTextFieldCustomizado(String hint, JComponent textField) {
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
        String tipoEscolhido = (String) cmbTipoUsuario.getSelectedItem();
        String login = edtLogin.getText().replaceAll("[.-]", "");
        String senha = new String(edtSenha.getPassword());

        try {
            if (tipoEscolhido.equals(TipoUsuario.GESTANTE.getValor())) {
                autenticacaoController.logarGestante(login, senha);
            } else if (tipoEscolhido.equals(TipoUsuario.MEDICO.getValor())) {
                autenticacaoController.logarMedico(login, senha);
            } else if (tipoEscolhido.equals(TipoUsuario.SECRETARIO.getValor())) {
                autenticacaoController.logarSecretario(login, senha);
            } else if (tipoEscolhido.equals(TipoUsuario.ADMIN.getValor())) {
                autenticacaoController.logarAdmin(login, senha);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um tipo de usuário válido");
                return;
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cmbUserTypeActionPerformed(java.awt.event.ActionEvent evt) {
        panFundo.repaint();
    }

    private void lblForgotPasswordActionPerformed(java.awt.event.MouseEvent evt) {
        String tipoEscolhido = (String) cmbTipoUsuario.getSelectedItem();
        String login = edtLogin.getText().replaceAll("[.-]", "");

        if (login == null || login.trim().isEmpty() || login.contains("_")) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o CPF da conta para recuperá-la.");
            return;
        }

        String codigoRecuperacao = String.format("%06d", (int) (Math.random() * 1000000));

        Usuario usuario = null;

        try {
            if (tipoEscolhido.equals(TipoUsuario.GESTANTE.getValor())) {
                usuario = gestanteController.adicionarCodigoRecuperacao(login, codigoRecuperacao);
            } else if (tipoEscolhido.equals(TipoUsuario.MEDICO.getValor())) {
                usuario = medicoController.adicionarCodigoRecuperacao(login, codigoRecuperacao);
            } else if (tipoEscolhido.equals(TipoUsuario.SECRETARIO.getValor())) {
                usuario = secretarioController.adicionarCodigoRecuperacao(login, codigoRecuperacao);
            } else if (tipoEscolhido.equals(TipoUsuario.ADMIN.getValor())) {
                usuario = adminController.adicionarCodigoRecuperacao(login, codigoRecuperacao);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um tipo de usuário válido");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        String email = usuario.getEmail();
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];
        String maskedUsername = username.substring(0, 4) + "*".repeat(username.length() - 4);
        String maskedEmail = maskedUsername + "@" + domain;
        JOptionPane.showMessageDialog(this,
                "Um código de recuperação está sendo enviado para o seu email: " + maskedEmail + ".");
        return;

    }

}
