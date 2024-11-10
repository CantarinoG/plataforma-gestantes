package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import com.cantarino.souza.view.components.*;

public class FrUsuarios extends JFrame {

    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JLabel lblAction;
    JPanel panCategories;
    JButton btnGestantes;
    JButton btnMedicos;
    JButton btnSecretarios;
    JButton btnAdmin;
    JPanel panTable;
    JPanel panOptions;
    JButton btnCadastrarUsuario;
    JButton btnEditarUsuario;
    JButton btnDeletarUsuario;

    public FrUsuarios() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        lblAction = new JLabel("Gestão de Usuários");
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panContent.add(lblAction, gbc);

        panCategories = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panCategories.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panContent.add(panCategories, gbc);

        btnGestantes = new RoundedButton("Paciente", 50);
        btnGestantes.setPreferredSize(new Dimension(200, 55));
        btnGestantes.setMinimumSize(new Dimension(200, 55));
        btnGestantes.setMaximumSize(new Dimension(200, 55));
        btnGestantes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGestantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestantesActionPerformed(evt);
            }
        });
        panCategories.add(btnGestantes);

        btnMedicos = new RoundedButton("Médico(a)", 50);
        btnMedicos.setPreferredSize(new Dimension(200, 55));
        btnMedicos.setMinimumSize(new Dimension(200, 55));
        btnMedicos.setMaximumSize(new Dimension(200, 55));
        btnMedicos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicosActionPerformed(evt);
            }
        });
        panCategories.add(btnMedicos);

        btnSecretarios = new RoundedButton("Secretário(a)", 50);
        btnSecretarios.setPreferredSize(new Dimension(200, 55));
        btnSecretarios.setMinimumSize(new Dimension(200, 55));
        btnSecretarios.setMaximumSize(new Dimension(200, 55));
        btnSecretarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSecretarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecretariosActionPerformed(evt);
            }
        });
        panCategories.add(btnSecretarios);

        btnAdmin = new RoundedButton("Administrador(a)", 50);
        btnAdmin.setPreferredSize(new Dimension(200, 55));
        btnAdmin.setMinimumSize(new Dimension(200, 55));
        btnAdmin.setMaximumSize(new Dimension(200, 55));
        btnAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        panCategories.add(btnAdmin);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        // panTable = new JPanel();
        // panTable.setBackground(AppColors.TITLE_BLUE);
        // panContent.add(panTable, gbc);

        {// Tabela mockada
            String[] columnNames = { "Data", "Hora", "Médico", "Especialidade", "Status" };
            Object[][] data = {
                    { "2024-03-20", "14:30", "Dr. Silva", "Obstetrícia", "Agendada" },
                    { "2024-03-25", "10:00", "Dra. Santos", "Ginecologia", "Agendada" }
            };

            JTable tblConsultas = new JTable(data, columnNames);
            tblConsultas.setFillsViewportHeight(true);
            tblConsultas.setRowHeight(30);
            tblConsultas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

            JScrollPane scrollPane = new JScrollPane(tblConsultas);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panContent.add(scrollPane, gbc);
        }

        panOptions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panOptions.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panContent.add(panOptions, gbc);

        btnCadastrarUsuario = new RoundedButton("Cadastrar Usuário", 50);
        btnCadastrarUsuario.setPreferredSize(new Dimension(200, 55));
        btnCadastrarUsuario.setMinimumSize(new Dimension(200, 55));
        btnCadastrarUsuario.setMaximumSize(new Dimension(200, 55));
        btnCadastrarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarUsuarioActionPerformed(evt);
            }
        });
        panOptions.add(btnCadastrarUsuario);

        btnEditarUsuario = new RoundedButton("Editar Usuário", 50);
        btnEditarUsuario.setPreferredSize(new Dimension(200, 55));
        btnEditarUsuario.setMinimumSize(new Dimension(200, 55));
        btnEditarUsuario.setMaximumSize(new Dimension(200, 55));
        btnEditarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });
        panOptions.add(btnEditarUsuario);

        btnDeletarUsuario = new RoundedButton("Deletar Usuário", 50);
        btnDeletarUsuario.setPreferredSize(new Dimension(200, 55));
        btnDeletarUsuario.setMinimumSize(new Dimension(200, 55));
        btnDeletarUsuario.setMaximumSize(new Dimension(200, 55));
        btnDeletarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeletarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarUsuarioActionPerformed(evt);
            }
        });
        panOptions.add(btnDeletarUsuario);

        panBackground.add(panContent, BorderLayout.CENTER);
    }

    private void btnGestantesActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de gestantes");
    }

    private void btnMedicosActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de medicos");
    }

    private void btnSecretariosActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de secretarios");
    }

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de admins");
    }

    private void btnCadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de cadastrar usuario");
    }

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de editar usuario");
    }

    private void btnDeletarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de deletar usuario");
    }

}
