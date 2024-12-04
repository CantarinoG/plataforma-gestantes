package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.AdminController;
import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.controller.SecretarioController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class DlgTrocaSenha extends JDialog {
    JPanel panFundo;
    JPasswordField edtSenha;
    JPasswordField edtConfirmarSenha;
    RoundedButton btnTrocarSenha;
    JLabel lblHint;
    JPanel panField;

    Usuario usuario = null;

    private AutenticacaoController autenticacaoController;

    public DlgTrocaSenha(JFrame parent, boolean modal) {
        super(parent, modal);

        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();

        initComponents();
    }

    private void initComponents() {
        setTitle("Troca de Senha");
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
        gbc.insets = new Insets(10, 0, 10, 0);

        edtSenha = new JPasswordField();
        configurarCampoSenha(edtSenha);
        panFundo.add(criarTextFieldCustomizado("Nova Senha", edtSenha), gbc);

        gbc.gridy++;
        edtConfirmarSenha = new JPasswordField();
        configurarCampoSenha(edtConfirmarSenha);
        panFundo.add(criarTextFieldCustomizado("Confirmar Nova Senha", edtConfirmarSenha), gbc);

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
        panFundo.add(btnTrocarSenha, gbc);
    }

    private void configurarCampoSenha(JPasswordField campo) {
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBackground(AppColors.FIELD_PINK);
        campo.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    private JPanel criarTextFieldCustomizado(String hint, JComponent textField) {
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

        if (usuario instanceof Gestante) {
            GestanteController controller = new GestanteController();
            controller.atualizarSenha((Gestante) usuario, senha);
        } else if (usuario instanceof Medico) {
            MedicoController controller = new MedicoController();
            controller.atualizarSenha((Medico) usuario, senha);
        } else if (usuario instanceof Secretario) {
            SecretarioController controller = new SecretarioController();
            controller.atualizarSenha((Secretario) usuario, senha);
        } else if (usuario instanceof Admin) {
            AdminController controller = new AdminController();
            controller.atualizarSenha((Admin) usuario, senha);
        }

        dispose();

    }
}
