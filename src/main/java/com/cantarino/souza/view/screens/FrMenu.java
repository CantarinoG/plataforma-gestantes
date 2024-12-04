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

    JPanel panFundo;
    JLabel lblIcone;
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
    JPanel panHeader;
    JPanel panDireito;
    JPanel panEsquerdo;
    JPanel panInfoUsuario;
    JPanel panTitulo;
    JLabel lblSubtitulo;
    JLabel lblSubtexto;
    JPanel panTextoCentral;
    JLabel lblCentral;
    JPanel panConteudo;
    JPanel panGrid;
    JPanel panCentral;

    private AutenticacaoController autenticacaoController;

    Usuario usuario = null;

    public FrMenu() {
        autenticacaoController = new AutenticacaoController();

        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1070);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panFundo = new BackgroundPanel("/images/background.png");
        panFundo.setLayout(new BorderLayout());
        setContentPane(panFundo);

        panHeader = new JPanel();
        panHeader.setPreferredSize(new Dimension(getWidth(), 138));
        panHeader.setBorder(BorderFactory.createEmptyBorder(10, 64, 10, 64));
        panHeader.setBackground(AppColors.BUTTON_PINK);
        panHeader.setOpaque(true);
        panHeader.setLayout(new BorderLayout());

        panDireito = new JPanel();
        panDireito.setLayout(new BoxLayout(panDireito, BoxLayout.X_AXIS));
        panDireito.setOpaque(false);

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

        panDireito.add(btnTrocarSenha);
        panDireito.add(Box.createRigidArea(new Dimension(20, 0)));
        panDireito.add(btnSair);

        panEsquerdo = new JPanel();
        panEsquerdo.setLayout(new BoxLayout(panEsquerdo, BoxLayout.Y_AXIS));
        panEsquerdo.setOpaque(false);

        panInfoUsuario = new JPanel();
        panInfoUsuario.setLayout(new BoxLayout(panInfoUsuario, BoxLayout.X_AXIS));
        panInfoUsuario.setOpaque(false);

        lblIcone = new JLabel(new ImageIcon(getClass().getResource("/images/anon.png")));
        panTitulo = new JPanel();
        panTitulo.setLayout(new BoxLayout(panTitulo, BoxLayout.Y_AXIS));
        panTitulo.setOpaque(false);

        lblSubtitulo = new JLabel("Convidado");
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblSubtexto = new JLabel("Bem-vindo ao sistema");
        lblSubtexto.setFont(new Font("Arial", Font.PLAIN, 14));

        panTitulo.add(lblSubtitulo);
        panTitulo.add(lblSubtexto);

        panInfoUsuario.add(lblIcone);
        panInfoUsuario.add(Box.createRigidArea(new Dimension(20, 0)));
        panInfoUsuario.add(panTitulo);

        panEsquerdo.add(panInfoUsuario);

        panTextoCentral = new JPanel();
        panTextoCentral.setOpaque(false);
        panTextoCentral.setLayout(new BoxLayout(panTextoCentral, BoxLayout.Y_AXIS));

        lblCentral = new JLabel("BemGestar");
        lblCentral.setFont(new Font("Arial", Font.BOLD, 28));
        lblCentral.setForeground(AppColors.TITLE_BLUE);
        lblCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
        panTextoCentral.add(lblCentral);

        panHeader.add(panDireito, BorderLayout.EAST);
        panHeader.add(panEsquerdo, BorderLayout.WEST);
        panHeader.add(panTextoCentral, BorderLayout.CENTER);
        panFundo.add(panHeader, BorderLayout.NORTH);

        panConteudo = new JPanel(new BorderLayout());
        panConteudo.setBorder(BorderFactory.createEmptyBorder(20, 64, 20, 64));
        panConteudo.setBackground(AppColors.TRANSPARENT);

        panGrid = new JPanel(new GridLayout(0, 2, 20, 20));
        panGrid.setOpaque(false);
        panGrid.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

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

        panGrid.add(btnLogin);
        panGrid.add(btnOrientacoes);

        panCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panCentral.setOpaque(false);
        panCentral.add(panGrid);
        panConteudo.add(panCentral, BorderLayout.CENTER);

        panFundo.add(panConteudo, BorderLayout.CENTER);

        btnSair.setVisible(false);
        btnTrocarSenha.setVisible(false);

    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        DlgLogin dialog = new DlgLogin(this, true);
        dialog.setVisible(true);
        usuario = autenticacaoController.getUsuario();

        if (usuario != null) {
            btnSair.setVisible(true);
            btnTrocarSenha.setVisible(true);
            lblSubtitulo.setText(usuario.getNome());
            lblSubtexto.setText(usuario.getEmail());
            panGrid.removeAll();

            if (usuario instanceof Gestante) {
                initGestanteComponents();
            } else if (usuario instanceof Medico) {
                initMedicoComponents();
            } else if (usuario instanceof Secretario) {
                initSecretarioComponents();
            } else if (usuario instanceof Admin) {
                initAdminComponents();
            }

            panGrid.revalidate();
            panGrid.repaint();
            repaint();
        }
    }

    private void initGestanteComponents() {
        lblIcone.setIcon(new ImageIcon(getClass().getResource("/images/patient.png")));
        panGrid.add(btnAgendaGestante);
        panGrid.add(btnPagamentos);
        panGrid.add(btnComunidade);
        panGrid.add(btnOrientacoes);
    }

    private void initMedicoComponents() {
        lblIcone.setIcon(new ImageIcon(getClass().getResource("/images/doctor.png")));
        panGrid.add(btnAgendaMedico);
        panGrid.add(btnComunidade);
        panGrid.add(btnOrientacoes);
    }

    private void initSecretarioComponents() {
        lblIcone.setIcon(new ImageIcon(getClass().getResource("/images/secretary.png")));
        panGrid.add(btnPagamentos);
        panGrid.add(btnConsultas);
        panGrid.add(btnExames);
        panGrid.add(btnGestantes);
        panGrid.add(btnMedicos);
        panGrid.add(btnSecretarios);
        panGrid.add(btnComunidade);
        panGrid.add(btnOrientacoes);
    }

    private void initAdminComponents() {
        lblIcone.setIcon(new ImageIcon(getClass().getResource("/images/adm.png")));
        panGrid.add(btnPagamentos);
        panGrid.add(btnConsultas);
        panGrid.add(btnExames);
        panGrid.add(btnGestantes);
        panGrid.add(btnMedicos);
        panGrid.add(btnSecretarios);
        panGrid.add(btnAdmins);
        panGrid.add(btnComunidade);
        panGrid.add(btnOrientacoes);
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
        autenticacaoController.deslogar();
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
