package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.view.components.*;

public class DlgAgendaConsultas extends JDialog {

        JPanel panBackground;
        JPanel panHeader;
        JLabel lblTitle;
        JPanel panContent;
        JPanel panLeft;
        JPanel panRight;
        JPanel panProfile;
        JPanel panCategories;
        JPanel panEmpty;
        JLabel lblProfileImg;
        JLabel lblUserData;
        JPanel panTodas;
        JPanel panAgendadas;
        JLabel lblTodasIcon;
        JLabel lblTodas;
        JLabel lblTodasAmount;
        JLabel lblAgendadasIcon;
        JLabel lblAgendadas;
        JLabel lblAgendadasAmount;
        JPanel panTop;
        JButton btnConsultas;
        JButton btnExames;
        JPanel panBottom;
        JPanel panInnerTop;
        JLabel lblAction;
        JPanel panMiddle;
        JPanel panInnerBottom;
        JPanel panActions;
        JButton btnCancelarConsulta;
        JButton btnVerRelatorio;
        JButton btnVerDadosMedico;
        JButton btnVerDadosPaciente;
        JButton btnCadastrarRelatorio;
        JButton btnEditarRelatorio;
        JPanel panConcluidas;
        JLabel lblConcluidasIcon;
        JLabel lblConcluidas;
        JLabel lblConcuildasAmount;
        JPanel panCanceladas;
        JLabel lblCanceladasIcon;
        JLabel lblCanceladas;
        JLabel lblCanceladasAmount;
        JTable grdConsultas;
        JScrollPane scrollPane;

        ConsultaController consultaController;

        public DlgAgendaConsultas(JFrame parent, boolean modal) {
                super(parent, modal);
                consultaController = new ConsultaController();
                initComponents();
                consultaController.atualizarTabela(grdConsultas);
        }

        private void initComponents() {
                setTitle("Agenda");
                setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                setSize(1920, 1080);
                setLocationRelativeTo(null);

                panBackground = new BackgroundPanel("/images/background.png");
                panBackground.setLayout(new BorderLayout());
                panBackground.setLayout(new GridBagLayout());
                panBackground.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                setContentPane(panBackground);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.insets = new Insets(0, 0, 10, 0);
                gbc.gridy = 0;
                gbc.weighty = 0.0;
                panTop = new JPanel();
                panTop.setBackground(AppColors.TRANSPARENT);
                panBackground.add(panTop, gbc);

                gbc.gridy = 1;
                gbc.weighty = 1.0;
                panBottom = new JPanel(new GridBagLayout());
                panBottom.setBackground(AppColors.TRANSPARENT);
                panBackground.add(panBottom, gbc);

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 0.35;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.insets = new Insets(0, 0, 0, 10);
                panLeft = new JPanel();
                panLeft.setBackground(AppColors.TRANSPARENT);
                panLeft.setPreferredSize(new Dimension(0, 0));
                panLeft.setLayout(new GridLayout(3, 1, 0, 10));
                panBottom.add(panLeft, gbc);

                gbc.gridx = 1;
                gbc.weightx = 0.65;
                gbc.insets = new Insets(0, 0, 0, 0);
                panRight = new JPanel();
                panRight.setBackground(AppColors.TRANSPARENT);
                panRight.setPreferredSize(new Dimension(0, 0));
                panRight.setLayout(new GridBagLayout());

                GridBagConstraints rightGbc = new GridBagConstraints();
                rightGbc.gridx = 0;
                rightGbc.gridy = 0;
                rightGbc.fill = GridBagConstraints.BOTH;
                rightGbc.weightx = 1.0;
                rightGbc.weighty = 0.0;
                panInnerTop = new JPanel();
                panInnerTop.setBackground(AppColors.TRANSPARENT);
                panRight.add(panInnerTop, rightGbc);

                lblAction = new JLabel("Consultas");
                lblAction.setFont(new Font("Arial", Font.BOLD, 64));
                lblAction.setForeground(
                                AppColors.TITLE_BLUE);
                panInnerTop.add(lblAction);

                rightGbc.gridy = 1;
                rightGbc.weighty = 1.0;

                grdConsultas = new JTable();
                grdConsultas.setModel(new DefaultTableModel(
                                new Object[][] {
                                                {},
                                                {},
                                                {},
                                                {}
                                },
                                new String[] {

                                }));
                grdConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                grdConsultasMouseClicked(evt);
                        }
                });
                grdConsultas.setFillsViewportHeight(true);
                scrollPane = new JScrollPane();
                scrollPane.setViewportView(grdConsultas);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panRight.add(scrollPane, rightGbc);

                rightGbc.gridy = 2;
                rightGbc.weighty = 0.0;
                panInnerBottom = new JPanel();
                panInnerBottom.setBackground(AppColors.TRANSPARENT);
                panRight.add(panInnerBottom, rightGbc);
                panBottom.add(panRight, gbc);

                panActions = new JPanel();
                panActions.setBackground(AppColors.TRANSPARENT);
                panInnerBottom.add(panActions);

                panProfile = new JPanel();
                panProfile.setBackground(AppColors.TRANSPARENT);
                panProfile.setLayout(new GridLayout(1, 2, 10, 0));
                panLeft.add(panProfile);

                panCategories = new JPanel();
                panCategories.setBackground(AppColors.TRANSPARENT);
                panCategories.setLayout(new GridLayout(4, 1, 0, 0));
                panLeft.add(panCategories);

                panTodas = new JPanel();
                panTodas.setBackground(AppColors.TRANSPARENT);
                panTodas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panTodas.setLayout(new BorderLayout());
                panTodas.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.BLACK),
                                BorderFactory.createEmptyBorder(0, 40, 0, 40)));
                panTodas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                panTodas.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                panTodasActionPerformed(evt);
                        }
                });
                panCategories.add(panTodas);

                lblTodasIcon = new JLabel(new ImageIcon(getClass().getResource("/images/appointmentsIcon.png")));
                panTodas.add(lblTodasIcon, BorderLayout.WEST);

                lblTodas = new JLabel("Todas as Consultas");
                lblTodas.setFont(new Font("Arial", Font.BOLD, 24));
                lblTodas.setHorizontalAlignment(SwingConstants.CENTER);
                panTodas.add(lblTodas, BorderLayout.CENTER);

                lblTodasAmount = new JLabel("(4)");
                lblTodasAmount.setFont(new Font("Arial", Font.BOLD, 24));
                lblTodasAmount.setForeground(AppColors.BUTTON_PINK);
                panTodas.add(lblTodasAmount, BorderLayout.EAST);

                panAgendadas = new JPanel();
                panAgendadas.setBackground(AppColors.TRANSPARENT);
                panAgendadas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panAgendadas.setLayout(new BorderLayout());
                panAgendadas.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.BLACK),
                                BorderFactory.createEmptyBorder(0, 40, 0, 40)));
                panAgendadas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                panAgendadas.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                panAgendadasActionPerformed(evt);
                        }
                });
                panCategories.add(panAgendadas);

                lblAgendadasIcon = new JLabel(new ImageIcon(getClass().getResource("/images/scheduledIcon.png")));
                panAgendadas.add(lblAgendadasIcon, BorderLayout.WEST);

                lblAgendadas = new JLabel("Consultas Agendadas");
                lblAgendadas.setFont(new Font("Arial", Font.BOLD, 24));
                lblAgendadas.setHorizontalAlignment(SwingConstants.CENTER);
                panAgendadas.add(lblAgendadas, BorderLayout.CENTER);

                lblAgendadasAmount = new JLabel("(2)");
                lblAgendadasAmount.setFont(new Font("Arial", Font.BOLD, 24));
                lblAgendadasAmount.setForeground(AppColors.BUTTON_PINK);
                panAgendadas.add(lblAgendadasAmount, BorderLayout.EAST);

                panConcluidas = new JPanel();
                panConcluidas.setBackground(AppColors.TRANSPARENT);
                panConcluidas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panConcluidas.setLayout(new BorderLayout());
                panConcluidas.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.BLACK),
                                BorderFactory.createEmptyBorder(0, 40, 0, 40)));
                panConcluidas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                panConcluidas.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                panConcluidasActionPerformed(evt);
                        }
                });
                panCategories.add(panConcluidas);

                lblConcluidasIcon = new JLabel(
                                new ImageIcon(getClass().getResource("/images/finishedIcon.png")));
                panConcluidas.add(lblConcluidasIcon, BorderLayout.WEST);

                lblConcluidas = new JLabel("Consultas Concluídas");
                lblConcluidas.setFont(new Font("Arial", Font.BOLD, 24));
                lblConcluidas.setHorizontalAlignment(SwingConstants.CENTER);
                panConcluidas.add(lblConcluidas, BorderLayout.CENTER);

                lblConcuildasAmount = new JLabel("(4)");
                lblConcuildasAmount.setFont(new Font("Arial", Font.BOLD, 24));
                lblConcuildasAmount.setForeground(AppColors.BUTTON_PINK);
                panConcluidas.add(lblConcuildasAmount, BorderLayout.EAST);

                panCanceladas = new JPanel();
                panCanceladas.setBackground(AppColors.TRANSPARENT);
                panCanceladas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panCanceladas.setLayout(new BorderLayout());
                panCanceladas.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.BLACK),
                                BorderFactory.createEmptyBorder(0, 40, 0, 40)));
                panCanceladas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                panCanceladas.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                panCanceladasActionPerformed(evt);
                        }
                });
                panCategories.add(panCanceladas);

                lblCanceladasIcon = new JLabel(
                                new ImageIcon(getClass().getResource("/images/canceledIcon.png")));
                panCanceladas.add(lblCanceladasIcon, BorderLayout.WEST);

                lblCanceladas = new JLabel("Consultas Canceladas");
                lblCanceladas.setFont(new Font("Arial", Font.BOLD, 24));
                lblCanceladas.setHorizontalAlignment(SwingConstants.CENTER);
                panCanceladas.add(lblCanceladas, BorderLayout.CENTER);

                lblCanceladasAmount = new JLabel("(4)");
                lblCanceladasAmount.setFont(new Font("Arial", Font.BOLD, 24));
                lblCanceladasAmount.setForeground(AppColors.BUTTON_PINK);
                panCanceladas.add(lblCanceladasAmount, BorderLayout.EAST);

                panEmpty = new JPanel();
                panEmpty.setBackground(AppColors.TRANSPARENT);
                panLeft.add(panEmpty);

                initUserCoponents();
        }

        private void initUserCoponents() {
                String userType = "GESTANTE";

                if (userType == "GESTANTE") {
                        initGestanteComponents();
                } else if (userType == "MEDICO") {
                        initMedicoComponents();
                }
        }

        private void initGestanteComponents() {
                lblProfileImg = new JLabel(new ImageIcon(getClass().getResource("/images/profile.png")));
                panProfile.add(lblProfileImg);

                // TODO: Botar dados reais do usuário
                lblUserData = new JLabel(
                                "<html>Paciente: Maria José<br>Clínica: BemGestar<br>Nascimento: 17/09/2001</html>");
                panProfile.add(lblUserData);

                btnConsultas = new RoundedButton("Consultas", 50);
                btnConsultas.setBackground(AppColors.BUTTON_PINK);
                btnConsultas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnConsultas.setPreferredSize(new Dimension(btnConsultas.getPreferredSize().width, 55));
                btnConsultas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnConsultasActionPerformed(evt);
                        }
                });
                panTop.add(btnConsultas);

                btnExames = new RoundedButton("Exames", 50);
                btnExames.setBackground(AppColors.BUTTON_PINK);
                btnExames.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnExames.setPreferredSize(new Dimension(btnExames.getPreferredSize().width, 55));
                btnExames.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnExamesActionPerformed(evt);
                        }
                });
                panTop.add(btnExames);

                panActions.setLayout(new GridLayout(1, 3, 10, 0));
                btnCancelarConsulta = new RoundedButton("Cancelar Consulta", 50);
                btnCancelarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnCancelarConsulta.setPreferredSize(new Dimension(btnCancelarConsulta.getPreferredSize().width, 55));
                btnCancelarConsulta.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCancelarConsultaActionPerformed(evt);
                        }
                });
                panActions.add(btnCancelarConsulta);

                btnVerRelatorio = new RoundedButton("Ver Relatório", 50);
                btnVerRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnVerRelatorio.setPreferredSize(new Dimension(btnVerRelatorio.getPreferredSize().width, 55));
                btnVerRelatorio.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnVerRelatorioActionPerformed(evt);
                        }
                });
                panActions.add(btnVerRelatorio);

                btnVerDadosMedico = new RoundedButton("Ver Dados do Médico", 50);
                btnVerDadosMedico.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnVerDadosMedico.setPreferredSize(new Dimension(btnVerDadosMedico.getPreferredSize().width, 55));
                btnVerDadosMedico.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnVerDadosMedicoActionPerformed(evt);
                        }
                });
                panActions.add(btnVerDadosMedico);
        }

        private void initMedicoComponents() {
                lblProfileImg = new JLabel(new ImageIcon(getClass().getResource("/images/profile_doctor.png")));
                panProfile.add(lblProfileImg);

                // TODO: Botar dados reais do usuários
                lblUserData = new JLabel(
                                "<html>Médico(a): Maria José<br>Clínica: BemGestar<br>Nascimento: 17/09/2001</html>");
                panProfile.add(lblUserData);

                panActions.setLayout(new GridLayout(1, 5, 10, 0));
                btnCancelarConsulta = new RoundedButton("Cancelar Consulta", 50);
                btnCancelarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnCancelarConsulta.setPreferredSize(new Dimension(btnCancelarConsulta.getPreferredSize().width, 55));
                btnCancelarConsulta.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCancelarConsultaActionPerformed(evt);
                        }
                });
                panActions.add(btnCancelarConsulta);

                btnVerRelatorio = new RoundedButton("Ver Relatório", 50);
                btnVerRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnVerRelatorio.setPreferredSize(new Dimension(btnVerRelatorio.getPreferredSize().width, 55));
                btnVerRelatorio.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnVerRelatorioActionPerformed(evt);
                        }
                });
                panActions.add(btnVerRelatorio);

                btnVerDadosPaciente = new RoundedButton("Ver Dados do Paciente", 50);
                btnVerDadosPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnVerDadosPaciente.setPreferredSize(new Dimension(btnVerDadosPaciente.getPreferredSize().width, 55));
                btnVerDadosPaciente.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnVerDadosPacienteActionPerformed(evt);
                        }
                });
                panActions.add(btnVerDadosPaciente);

                btnCadastrarRelatorio = new RoundedButton("Cadastrar Relatório", 50);
                btnCadastrarRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnCadastrarRelatorio
                                .setPreferredSize(new Dimension(btnCadastrarRelatorio.getPreferredSize().width, 55));
                btnCadastrarRelatorio.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCadastrarRelatorioActionPerformed(evt);
                        }
                });
                panActions.add(btnCadastrarRelatorio);

                btnEditarRelatorio = new RoundedButton("Ver Dados do Paciente", 50);
                btnEditarRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnEditarRelatorio.setPreferredSize(new Dimension(btnEditarRelatorio.getPreferredSize().width, 55));
                btnEditarRelatorio.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEditarRelatorioActionPerformed(evt);
                        }
                });
                panActions.add(btnEditarRelatorio);

        }

        private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Clicou no botão de consultas!");
        }

        private void btnExamesActionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Clicou no botão de exames!");
        }

        private void panTodasActionPerformed(java.awt.event.MouseEvent evt) {
                System.out.println("Clicou no botão de todas consultas!");
        }

        private void panAgendadasActionPerformed(java.awt.event.MouseEvent evt) {
                System.out.println("Clicou no botão de consultas agendadas!");
        }

        private void panConcluidasActionPerformed(java.awt.event.MouseEvent evt) {
                System.out.println("Clicou no botão de consultas concluidas!");
        }

        private void panCanceladasActionPerformed(java.awt.event.MouseEvent evt) {
                System.out.println("Clicou no botão de consultas canceladas!");
        }

        private void btnCancelarConsultaActionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Clicou no botão de cancelar consulta!");
        }

        private void btnVerRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Clicou no botão de ver relatorio!");
        }

        private void btnVerDadosMedicoActionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Clicou no botão de ver dados medicos!");
        }

        private void btnVerDadosPacienteActionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Clicou no botão de ver dados do paciente!");
        }

        private void btnCadastrarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Clicou no botão de cadastrar relatorios!");
        }

        private void btnEditarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Clicou no botão de editar relatorios!");
        }

        private void grdConsultasMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_grdAlunosMouseClicked
                if (evt.getClickCount() == 2) {

                }
        }// GE

}
