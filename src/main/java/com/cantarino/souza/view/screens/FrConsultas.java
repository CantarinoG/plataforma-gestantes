package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import com.cantarino.souza.view.components.*;

public class FrConsultas extends JFrame {

    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JLabel lblAction;
    JPanel panCategories;
    JButton btnConsultas;
    JButton btnExames;
    JPanel panTable;
    JPanel panOptions;
    JButton btnCadastrarConsulta;
    JButton btnEditarConsulta;
    JButton btnDeletarConsulta;

    public FrConsultas() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Consultas");
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

        lblAction = new JLabel("Gestão de Procedimentos");
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

        btnConsultas = new RoundedButton("Consultas", 50);
        btnConsultas.setPreferredSize(new Dimension(200, 55));
        btnConsultas.setMinimumSize(new Dimension(200, 55));
        btnConsultas.setMaximumSize(new Dimension(200, 55));
        btnConsultas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultasActionPerformed(evt);
            }
        });
        panCategories.add(btnConsultas);

        btnExames = new RoundedButton("Exames", 50);
        btnExames.setPreferredSize(new Dimension(200, 55));
        btnExames.setMinimumSize(new Dimension(200, 55));
        btnExames.setMaximumSize(new Dimension(200, 55));
        btnExames.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExamesActionPerformed(evt);
            }
        });
        panCategories.add(btnExames);

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

        btnCadastrarConsulta = new RoundedButton("Cadastrar Consulta", 50);
        btnCadastrarConsulta.setPreferredSize(new Dimension(200, 55));
        btnCadastrarConsulta.setMinimumSize(new Dimension(200, 55));
        btnCadastrarConsulta.setMaximumSize(new Dimension(200, 55));
        btnCadastrarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarConsultaActionPerformed(evt);
            }
        });
        panOptions.add(btnCadastrarConsulta);

        btnEditarConsulta = new RoundedButton("Editar Consulta", 50);
        btnEditarConsulta.setPreferredSize(new Dimension(200, 55));
        btnEditarConsulta.setMinimumSize(new Dimension(200, 55));
        btnEditarConsulta.setMaximumSize(new Dimension(200, 55));
        btnEditarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarConsultaActionPerformed(evt);
            }
        });
        panOptions.add(btnEditarConsulta);

        btnDeletarConsulta = new RoundedButton("Deletar Consulta", 50);
        btnDeletarConsulta.setPreferredSize(new Dimension(200, 55));
        btnDeletarConsulta.setMinimumSize(new Dimension(200, 55));
        btnDeletarConsulta.setMaximumSize(new Dimension(200, 55));
        btnDeletarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeletarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarConsultaActionPerformed(evt);
            }
        });
        panOptions.add(btnDeletarConsulta);

        panBackground.add(panContent, BorderLayout.CENTER);
    }

    private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de consultas");
    }

    private void btnExamesActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de exames");
    }

    private void btnCadastrarConsultaActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de cadastrar consulta");
    }

    private void btnEditarConsultaActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de editar consulta");
    }

    private void btnDeletarConsultaActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de deletar consulta");
    }

}
