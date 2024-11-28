package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
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
                new String[] { "Todos", StatusProcedimentos.AGENDADA.getValue(),
                        StatusProcedimentos.CONCLUIDA.getValue(), StatusProcedimentos.CANCELADA.getValue() });
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

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdConsultas.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdConsultas.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;

        if (consulta.getStatus().equals(StatusProcedimentos.CANCELADA.getValue())) {
            JOptionPane.showMessageDialog(this, "Consulta já cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (consulta.getStatus().equals(StatusProcedimentos.CONCLUIDA.getValue())) {
            JOptionPane.showMessageDialog(this, "Consulta já concluída", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            int option = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja cancelar esta consulta?",
                    "Confirmar cancelamento",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                id = consulta.getId();
                consultaController.cancelar(id);
                cbFilterActionPerformed(null);

                JOptionPane.showMessageDialog(this, "Consulta cancelada com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return;
        }
    }

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;
        if (consulta.getRelatorio() == null) {
            JOptionPane.showMessageDialog(this, "Essa consulta não possui nenhum relatório cadastrado", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    private void btnMedicoActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;
        id = consulta.getMedico().getId();

        JDialog parentWindow = (JDialog) SwingUtilities.getWindowAncestor(this);
        DlgDadosMedico dialog = new DlgDadosMedico(parentWindow, true, id);
        dialog.setVisible(true);

    }

    private void cbFilterActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedFilter = (String) cbFilter.getSelectedItem();
        if (selectedFilter.equals("Todos")) {
            consultaController.filtrarTabelaPorIdGestante(grdConsultas, usuario.getId());
        } else {
            consultaController.filtrarTabelaPorIdGestanteStatus(grdConsultas, usuario.getId(), selectedFilter);
        }
    }

}
