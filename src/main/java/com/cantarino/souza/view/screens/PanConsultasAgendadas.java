package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.AuthTemp;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.RoundedButton;

import java.awt.*;

public class PanConsultasAgendadas extends JPanel {

    JTable grdConsultas;
    JScrollPane scrollPane;
    JPanel panFooter;
    JLabel lblFilter;
    JComboBox<String> cbFilter;
    JButton btnCancelar;
    JButton btnRelatorio;
    JButton btnMedico;

    private ConsultaController consultaController;

    private Usuario usuario;

    public PanConsultasAgendadas() {
        initComponents();
        usuario = AuthTemp.getInstance().getUsuario();
        consultaController = new ConsultaController();
        consultaController.filtrarTabelaPorIdGestante(grdConsultas, usuario.getId());
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));
        setOpaque(true);

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
        scrollPane = new JScrollPane(grdConsultas);
        add(scrollPane, BorderLayout.CENTER);

        panFooter = new JPanel();
        panFooter.setPreferredSize(new Dimension(getWidth(), 80));
        panFooter.setBackground(AppColors.BUTTON_PINK);
        panFooter.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

        lblFilter = new JLabel("Filtrar por:");
        cbFilter = new JComboBox<>(
                new String[] { "Todos", "Agendados", "Concluídos", "Cancelados" });
        cbFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFilterActionPerformed(evt);
            }
        });

        btnCancelar = new RoundedButton("Cancelar consulta", 10);
        btnCancelar.setPreferredSize(new Dimension(150, 50));
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRelatorio = new RoundedButton("Ver relatório", 10);
        btnRelatorio.setPreferredSize(new Dimension(150, 50));
        btnRelatorio.setBackground(Color.WHITE);
        btnRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });

        btnMedico = new RoundedButton("Ver dados do médico", 10);
        btnMedico.setPreferredSize(new Dimension(150, 50));
        btnMedico.setBackground(Color.WHITE);
        btnMedico.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicoActionPerformed(evt);
            }
        });

        panFooter.add(lblFilter);
        panFooter.add(cbFilter);
        panFooter.add(btnCancelar);
        panFooter.add(btnRelatorio);
        panFooter.add(btnMedico);

        add(panFooter, BorderLayout.SOUTH);
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnMedicoActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cbFilterActionPerformed(java.awt.event.ActionEvent evt) {
    }

}
