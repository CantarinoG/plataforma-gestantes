package com.cantarino.souza.view.screens;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import com.cantarino.souza.view.components.*;

public class FrMenu extends JFrame {

    JPanel panBackground;
    JLabel lblTitle;
    JPanel middlePanel;
    JLabel lblImage;
    JButton btnLogin;
    JButton btnOrientacoes;
    JButton btnAgenda;
    JButton btnPagamentos;
    JButton btnComunidade;
    JButton btnTrocarSenha;
    JButton btnConsultas;
    JButton btnUsuarios;

    public FrMenu() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new GridBagLayout());
        panBackground.setBorder(BorderFactory.createEmptyBorder(20, 64, 20, 64));
        setContentPane(panBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        gbc.weighty = 0.0;
        lblTitle = new JLabel("Bem-vindo(a) ao BemGestar");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(AppColors.TITLE_BLUE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.anchor = GridBagConstraints.CENTER;
        panBackground.add(lblTitle, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(AppColors.TRANSPARENT);
        panBackground.add(middlePanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lblImage = new JLabel(new ImageIcon(getClass().getResource("/images/menu.png")));
        panBackground.add(lblImage, gbc);

        btnLogin = new RoundedButton("Login", 50);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setPreferredSize(new Dimension(200, 40));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnOrientacoes = new RoundedButton("Orientações", 50);
        btnOrientacoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOrientacoes.setPreferredSize(new Dimension(200, 40));
        btnOrientacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrientacoesActionPerformed(evt);
            }
        });

        btnAgenda = new RoundedButton("Agenda", 50);
        btnAgenda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgenda.setPreferredSize(new Dimension(200, 40));
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });

        btnPagamentos = new RoundedButton("Pagamentos", 50);
        btnPagamentos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPagamentos.setPreferredSize(new Dimension(200, 40));
        btnPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagamentosActionPerformed(evt);
            }
        });

        btnComunidade = new RoundedButton("Comunidade", 50);
        btnComunidade.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComunidade.setPreferredSize(new Dimension(200, 40));
        btnComunidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComunidadeActionPerformed(evt);
            }
        });

        btnTrocarSenha = new RoundedButton("Trocar Senha", 50);
        btnTrocarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTrocarSenha.setPreferredSize(new Dimension(200, 40));
        btnTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarSenhaActionPerformed(evt);
            }
        });

        btnConsultas = new RoundedButton("Consultas", 50);
        btnConsultas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConsultas.setPreferredSize(new Dimension(200, 40));
        btnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultasActionPerformed(evt);
            }
        });

        btnUsuarios = new RoundedButton("Usuarios", 50);
        btnUsuarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUsuarios.setPreferredSize(new Dimension(200, 40));
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        initUserCoponents();
    }

    // TODO: Assim está mockado. Refazer depois quando a autenticação estiver
    // pronta.
    private void initUserCoponents() {
        String userType = "ADM";

        if (userType == "GESTANTE") {
            initGestanteComponents();
        } else if (userType == "MEDICO") {
            initMedicoComponents();
        } else if (userType == "SECRETARIO" || userType == "ADM") {
            initSecOrAdmComponents();
        } else {
            initVisitorComponents();
        }
    }

    private void initVisitorComponents() {
        GridBagConstraints middleGbc = new GridBagConstraints();
        middleGbc.gridx = 0;
        middleGbc.insets = new Insets(5, 0, 5, 0);
        middleGbc.anchor = GridBagConstraints.CENTER;

        middleGbc.gridy = 0;
        middlePanel.add(btnLogin, middleGbc);

        middleGbc.gridy = 1;
        middlePanel.add(btnOrientacoes, middleGbc);
    }

    private void initGestanteComponents() {
        // TODO: Botar nome do usuário
        lblTitle.setText("Bem-vinda Fulana!");

        GridBagConstraints middleGbc = new GridBagConstraints();
        middleGbc.gridx = 0;
        middleGbc.insets = new Insets(5, 0, 5, 0);
        middleGbc.anchor = GridBagConstraints.CENTER;

        middleGbc.gridy = 0;
        middlePanel.add(btnAgenda, middleGbc);

        middleGbc.gridy = 1;
        middlePanel.add(btnPagamentos, middleGbc);

        middleGbc.gridy = 2;
        middlePanel.add(btnComunidade, middleGbc);

        middleGbc.gridy = 3;
        middlePanel.add(btnOrientacoes, middleGbc);

        middleGbc.gridy = 4;
        middlePanel.add(btnTrocarSenha, middleGbc);
    }

    private void initMedicoComponents() {
        // TODO: Botar nome do usuário
        lblTitle.setText("Bem-vindo(a) Fulano!");

        GridBagConstraints middleGbc = new GridBagConstraints();
        middleGbc.gridx = 0;
        middleGbc.insets = new Insets(5, 0, 5, 0);
        middleGbc.anchor = GridBagConstraints.CENTER;

        middleGbc.gridy = 0;
        middlePanel.add(btnAgenda, middleGbc);

        middleGbc.gridy = 1;
        middlePanel.add(btnComunidade, middleGbc);

        middleGbc.gridy = 2;
        middlePanel.add(btnOrientacoes, middleGbc);

        middleGbc.gridy = 3;
        middlePanel.add(btnTrocarSenha, middleGbc);

    }

    private void initSecOrAdmComponents() {
        // TODO: Botar nome do usuário
        lblTitle.setText("Bem-vindo(a) Fulano!");

        GridBagConstraints middleGbc = new GridBagConstraints();
        middleGbc.gridx = 0;
        middleGbc.insets = new Insets(5, 0, 5, 0);
        middleGbc.anchor = GridBagConstraints.CENTER;

        middleGbc.gridy = 0;
        middlePanel.add(btnPagamentos, middleGbc);

        middleGbc.gridy = 1;
        middlePanel.add(btnConsultas, middleGbc);

        middleGbc.gridy = 2;
        middlePanel.add(btnUsuarios, middleGbc);

        middleGbc.gridy = 3;
        middlePanel.add(btnComunidade, middleGbc);

        middleGbc.gridy = 4;
        middlePanel.add(btnOrientacoes, middleGbc);

        middleGbc.gridy = 5;
        middlePanel.add(btnTrocarSenha, middleGbc);

    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de login!");
    }

    private void btnOrientacoesActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de orientações!");
    }

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de agenda!");
    }

    private void btnPagamentosActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de pagamentos!");
    }

    private void btnComunidadeActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de comunidade!");
    }

    private void btnTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de trocar senha!");
    }

    private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de consultas!");
    }

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de usuários!");
    }

}
