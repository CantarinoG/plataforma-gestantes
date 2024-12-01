package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.cantarino.souza.controller.ExameController;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.view.AuthTemp;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.RoundedButton;

public class PanExamesAgendados extends JPanel {

    JTable grdExames;
    JScrollPane scrollPane;
    JPanel panFooter;
    JLabel lblFilter;
    JComboBox<String> cbFilter;
    JButton btnCancelar;
    JButton btnRelatorio;

    private ExameController exameController;

    private Usuario usuario;

    public PanExamesAgendados() {
        initComponents();
        usuario = AuthTemp.getInstance().getUsuario();
        exameController = new ExameController();
        exameController.filtrarTabelaPorIdGestante(grdExames, usuario.getId());
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));
        setOpaque(true);

        grdExames = new JTable();
        grdExames.setModel(new DefaultTableModel(
                new Object[][] {
                        {},
                        {},
                        {},
                        {}
                },
                new String[] {
                }));
        scrollPane = new JScrollPane(grdExames);
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

        btnCancelar = new RoundedButton("Cancelar exame", 10);
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

        panFooter.add(lblFilter);
        panFooter.add(cbFilter);
        panFooter.add(btnCancelar);
        panFooter.add(btnRelatorio);

        add(panFooter, BorderLayout.SOUTH);
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame consulta = (Exame) selectedObject;

        if (consulta.getStatus().equals(StatusProcedimentos.CANCELADA.getValue())) {
            JOptionPane.showMessageDialog(this, "Exame já cancelado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (consulta.getStatus().equals(StatusProcedimentos.CONCLUIDA.getValue())) {
            JOptionPane.showMessageDialog(this, "Exame já concluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            int option = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja cancelar este exame?",
                    "Confirmar cancelamento",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                id = consulta.getId();
                exameController.cancelar(id);
                cbFilterActionPerformed(null);

                JOptionPane.showMessageDialog(this, "Exame cancelado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return;
        }
    }

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;
        if (exame.getRelatorio() == null) {
            JOptionPane.showMessageDialog(this, "Esse exame não possui nenhum relatório cadastrado", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            JDialog parentWindow = (JDialog) SwingUtilities.getWindowAncestor(this);
            DlgDadosRelatorio dialog = new DlgDadosRelatorio(parentWindow, true, exame);
            dialog.setVisible(true);
        }
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdExames.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdExames.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void cbFilterActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedFilter = (String) cbFilter.getSelectedItem();
        if (selectedFilter.equals("Todos")) {
            exameController.filtrarTabelaPorIdGestante(grdExames, usuario.getId());
        } else {
            exameController.filtrarTabelaPorIdGestanteStatus(grdExames, usuario.getId(), selectedFilter);
        }
    }
}
