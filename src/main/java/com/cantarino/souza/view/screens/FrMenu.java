package com.cantarino.souza.view.screens;

import java.awt.*;

import javax.swing.*;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.components.*;

public class FrMenu extends JFrame {

    JPanel panBackground;
    JLabel lblIcon;
    JButton btnTrocarSenha;
    JButton btnOrientacoes;
    JButton btnAgendaGestante;
    JButton btnAgendaMedico;
    JButton btnPagamentos;
    JButton btnComunidade;
    JButton btnConsultas;
    JButton btnSair;
    JButton btnLogin;
    JButton btnExames;
    JButton btnGestantes;
    JButton btnMedicos;
    JButton btnSecretarios;
    JButton btnAdmins;
    JPanel headerPanel;
    JPanel rightButtomPanel;
    JPanel leftLabelsPanel;
    JPanel userInfoPanel;
    JPanel titlePanel;
    JLabel lblSubtitle;
    JLabel lblSubtext;
    JPanel centerTextPanel;
    JLabel centerLabel;
    JPanel contentPanel;
    JLabel titleLabel;
    JPanel gridPanel;
    JPanel centeringPanel;

    private AutenticacaoController autenticacaoController;

    Usuario userAutenticado = null;

    public FrMenu() {
        initComponents();
        autenticacaoController = new AutenticacaoController();
    }

    private void initComponents() {
        setTitle("BemGestar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1070);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new BorderLayout());
        setContentPane(panBackground);

        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(getWidth(), 138));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 64, 10, 64));
        headerPanel.setBackground(AppColors.BUTTON_PINK);
        headerPanel.setOpaque(true);
        headerPanel.setLayout(new BorderLayout());

        rightButtomPanel = new JPanel();
        rightButtomPanel.setLayout(new BoxLayout(rightButtomPanel, BoxLayout.X_AXIS));
        rightButtomPanel.setOpaque(false);

        btnTrocarSenha = new RoundedButton("Trocar Senha", 10);
        btnTrocarSenha.setBackground(Color.white);
        btnTrocarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTrocarSenha.setPreferredSize(new Dimension(150, 50));
        btnTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarSenhaActionPerformed(evt);
            }
        });

        btnSair = new RoundedButton("Sair da Conta", 10);
        btnSair.setBackground(Color.white);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.setPreferredSize(new Dimension(150, 50));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        rightButtomPanel.add(btnTrocarSenha);
        rightButtomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        rightButtomPanel.add(btnSair);

        leftLabelsPanel = new JPanel();
        leftLabelsPanel.setLayout(new BoxLayout(leftLabelsPanel, BoxLayout.Y_AXIS));
        leftLabelsPanel.setOpaque(false);

        userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.X_AXIS));
        userInfoPanel.setOpaque(false);

        lblIcon = new JLabel(new ImageIcon(getClass().getResource("/images/anon.png")));
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        lblSubtitle = new JLabel("Convidado");
        lblSubtitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblSubtext = new JLabel("Bem-vindo ao sistema");
        lblSubtext.setFont(new Font("Arial", Font.PLAIN, 14));

        titlePanel.add(lblSubtitle);
        titlePanel.add(lblSubtext);

        userInfoPanel.add(lblIcon);
        userInfoPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        userInfoPanel.add(titlePanel);

        leftLabelsPanel.add(userInfoPanel);

        centerTextPanel = new JPanel();
        centerTextPanel.setOpaque(false);
        centerTextPanel.setLayout(new BoxLayout(centerTextPanel, BoxLayout.Y_AXIS));

        centerLabel = new JLabel("BemGestar");
        centerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        centerLabel.setForeground(AppColors.TITLE_BLUE);
        centerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerTextPanel.add(centerLabel);

        headerPanel.add(rightButtomPanel, BorderLayout.EAST);
        headerPanel.add(leftLabelsPanel, BorderLayout.WEST);
        headerPanel.add(centerTextPanel, BorderLayout.CENTER);
        panBackground.add(headerPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 64, 20, 64));
        contentPanel.setBackground(AppColors.TRANSPARENT);

        gridPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        btnOrientacoes = new RoundedButton("Orientações", 20);
        btnOrientacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrientacoesActionPerformed(evt);
            }
        });

        btnAgendaGestante = new RoundedButton("Agenda", 20);
        btnAgendaGestante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaGestanteActionPerformed(evt);
            }
        });

        btnAgendaMedico = new RoundedButton("Agenda", 20);
        btnAgendaMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaMedicoActionPerformed(evt);
            }
        });

        btnPagamentos = new RoundedButton("Pagamentos", 20);
        btnPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagamentosActionPerformed(evt);
            }
        });

        btnComunidade = new RoundedButton("Comunidade", 20);
        btnComunidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComunidadeActionPerformed(evt);
            }
        });

        btnConsultas = new RoundedButton("Consultas", 20);
        btnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultasActionPerformed(evt);
            }
        });

        btnLogin = new RoundedButton("Login", 20);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnExames = new RoundedButton("Exames", 20);
        btnExames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExamesActionPerformed(evt);
            }
        });

        btnConsultas = new RoundedButton("Consultas", 20);
        btnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultasActionPerformed(evt);
            }
        });

        btnGestantes = new RoundedButton("Gestantes", 20);
        btnGestantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestantesActionPerformed(evt);
            }
        });

        btnMedicos = new RoundedButton("Médicos", 20);
        btnMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicosActionPerformed(evt);
            }
        });

        btnSecretarios = new RoundedButton("Secretários", 20);
        btnSecretarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecretariosActionPerformed(evt);
            }
        });

        btnAdmins = new RoundedButton("Administradores", 20);
        btnAdmins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminsActionPerformed(evt);
            }
        });

        JButton[] buttons = { btnOrientacoes, btnAgendaGestante, btnPagamentos, btnComunidade, btnConsultas, btnLogin,
                btnExames, btnGestantes, btnMedicos, btnSecretarios, btnAdmins, btnAgendaMedico };
        for (JButton button : buttons) {
            button.setBackground(AppColors.BUTTON_PINK);
            button.setForeground(Color.WHITE);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setPreferredSize(new Dimension(300, 100));
            button.setFont(new Font("Arial", Font.BOLD, 18));
        }

        gridPanel.add(btnLogin);
        gridPanel.add(btnOrientacoes);

        centeringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeringPanel.setOpaque(false);
        centeringPanel.add(gridPanel);
        contentPanel.add(centeringPanel, BorderLayout.CENTER);

        panBackground.add(contentPanel, BorderLayout.CENTER);

        btnSair.setVisible(false);
        btnTrocarSenha.setVisible(false);

    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        DlgLogin dialog = new DlgLogin(this, true);
        dialog.setVisible(true);
        userAutenticado = autenticacaoController.getUsuario();

        if (userAutenticado != null) {
            btnSair.setVisible(true);
            btnTrocarSenha.setVisible(true);
            lblSubtitle.setText(userAutenticado.getNome());
            lblSubtext.setText(userAutenticado.getEmail());
            gridPanel.removeAll();

            if (userAutenticado instanceof Gestante) {
                initGestanteComponents();
            } else if (userAutenticado instanceof Medico) {
                initMedicoComponents();
            } else if (userAutenticado instanceof Secretario) {
                initSecretarioComponents();
            } else if (userAutenticado instanceof Admin) {
                initAdminComponents();
            }

            gridPanel.revalidate();
            gridPanel.repaint();
            repaint();
        }
    }

    private void initGestanteComponents() {
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/images/patient.png")));
        gridPanel.add(btnAgendaGestante);
        gridPanel.add(btnPagamentos);
        gridPanel.add(btnComunidade);
        gridPanel.add(btnOrientacoes);
    }

    private void initMedicoComponents() {
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/images/doctor.png")));
        gridPanel.add(btnAgendaMedico);
        gridPanel.add(btnComunidade);
        gridPanel.add(btnOrientacoes);
    }

    private void initSecretarioComponents() {
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/images/secretary.png")));
        gridPanel.add(btnPagamentos);
        gridPanel.add(btnConsultas);
        gridPanel.add(btnExames);
        gridPanel.add(btnGestantes);
        gridPanel.add(btnMedicos);
        gridPanel.add(btnSecretarios);
        gridPanel.add(btnComunidade);
        gridPanel.add(btnOrientacoes);
    }

    private void initAdminComponents() {
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/images/adm.png")));
        gridPanel.add(btnPagamentos);
        gridPanel.add(btnConsultas);
        gridPanel.add(btnExames);
        gridPanel.add(btnGestantes);
        gridPanel.add(btnMedicos);
        gridPanel.add(btnSecretarios);
        gridPanel.add(btnAdmins);
        gridPanel.add(btnComunidade);
        gridPanel.add(btnOrientacoes);
    }

    private void btnOrientacoesActionPerformed(java.awt.event.ActionEvent evt) {
        DlgOrientacoes dialog = new DlgOrientacoes(this, true);
        dialog.setVisible(true);
    }

    private void btnAgendaGestanteActionPerformed(java.awt.event.ActionEvent evt) {
        DlgAgendaGestante dialog = new DlgAgendaGestante(this, true);
        dialog.setVisible(true);
    }

    private void btnAgendaMedicoActionPerformed(java.awt.event.ActionEvent evt) {
        DlgAgendaMedico dialog = new DlgAgendaMedico(this, true);
        dialog.setVisible(true);
    }

    private void btnPagamentosActionPerformed(java.awt.event.ActionEvent evt) {
        DlgPagamentos dialog = new DlgPagamentos(this, true);
        dialog.setVisible(true);
    }

    private void btnComunidadeActionPerformed(java.awt.event.ActionEvent evt) {
        DlgComunidade dialog = new DlgComunidade(this, true);
        dialog.setVisible(true);
    }

    private void btnTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {
        DlgTrocaSenha dialog = new DlgTrocaSenha(this, true);
        dialog.setVisible(true);
    }

    private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {
        DlgConsultas dialog = new DlgConsultas(this, true);
        dialog.setVisible(true);
    }

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {
        autenticacaoController.logOut();
        initComponents();
        repaint();
    }

    private void btnGestantesActionPerformed(java.awt.event.ActionEvent evt) {
        DlgGestantes dialog = new DlgGestantes(this, true);
        dialog.setVisible(true);
    }

    private void btnMedicosActionPerformed(java.awt.event.ActionEvent evt) {
        DlgMedicos dialog = new DlgMedicos(this, true);
        dialog.setVisible(true);
    }

    private void btnSecretariosActionPerformed(java.awt.event.ActionEvent evt) {
        DlgSecretarios dialog = new DlgSecretarios(this, true);
        dialog.setVisible(true);
    }

    private void btnAdminsActionPerformed(java.awt.event.ActionEvent evt) {
        DlgAdmins dialog = new DlgAdmins(this, true);
        dialog.setVisible(true);
    }

    private void btnExamesActionPerformed(java.awt.event.ActionEvent evt) {
        DlgExames dialog = new DlgExames(this, true);
        dialog.setVisible(true);
    }

}
