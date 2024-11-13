package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.view.components.*;

public class DlgConsultas extends JDialog {

    JPanel panBackground;
    JLabel lblAction;
    JPanel panCategories;
    JButton btnConsultas;
    JButton btnExames;
    JPanel panTable;
    JPanel panOptions;
    JButton btnCadastrarConsulta;
    JButton btnEditarConsulta;
    JButton btnDeletarConsulta;
    JTable grdProcedimentos;
    JScrollPane scrollPane;

    public DlgConsultas(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciador de Consultas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new GridBagLayout());
        setContentPane(panBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        lblAction = new JLabel("Gestão de Procedimentos");
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        panCategories = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panCategories.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panBackground.add(panCategories, gbc);

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

        grdProcedimentos = new JTable();
        grdProcedimentos.setModel(new DefaultTableModel(
                new Object[][] {
                        {},
                        {},
                        {},
                        {}
                },
                new String[] {
                }));
        grdProcedimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdProcedimentosMouseClicked(evt);
            }
        });
        grdProcedimentos.setFillsViewportHeight(true);
        grdProcedimentos.setBackground(AppColors.FIELD_PINK);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(grdProcedimentos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panBackground.add(scrollPane, gbc);

        panOptions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panOptions.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panBackground.add(panOptions, gbc);

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
    }

    private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {
        lblAction.setText("Gestão de Consultas");
        btnCadastrarConsulta.setText("Cadastrar Consulta");
        btnEditarConsulta.setText("Editar Consulta");
        btnDeletarConsulta.setText("Deletar Consulta");
        panBackground.repaint();

    }

    private void btnExamesActionPerformed(java.awt.event.ActionEvent evt) {
        lblAction.setText("Gestão de Exames");
        btnCadastrarConsulta.setText("Cadastrar Exame");
        btnEditarConsulta.setText("Editar Exame");
        btnDeletarConsulta.setText("Deletar Exame");
        panBackground.repaint();
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

    private void grdProcedimentosMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {

        }
    }

}
