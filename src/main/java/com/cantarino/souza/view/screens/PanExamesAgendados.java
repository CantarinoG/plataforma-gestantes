package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.ExameController;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.RoundedButton;

public class PanExamesAgendados extends JPanel {

    JTable grdExames;
    JScrollPane scrollPane;
    JPanel panFooter;
    JLabel lblFiltro;
    JComboBox<String> cbFiltro;
    JButton btnCancelar;
    JButton btnRelatorio;
    JButton btnMedico;
    JButton btnGestante;

    private ExameController exameController;
    private AutenticacaoController autenticacaoController;

    private Usuario usuario;

    public PanExamesAgendados() {
        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();
        exameController = new ExameController();

        initComponents();

        if (usuario instanceof Gestante) {
            exameController.atualizarTabelaPorGestante(grdExames, usuario.getId());
        } else {
            exameController.atualizarTabelaPorMedico(grdExames, usuario.getId());
        }

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

        lblFiltro = new JLabel("Filtrar por:");
        cbFiltro = new JComboBox<>(
                new String[] { "Todos", StatusProcedimentos.AGENDADA.getValor(),
                        StatusProcedimentos.CONCLUIDA.getValor(), StatusProcedimentos.CANCELADA.getValor() });
        cbFiltro.addActionListener(new java.awt.event.ActionListener() {
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

        btnMedico = new RoundedButton("Ver dados do médico", 10);
        btnMedico.setPreferredSize(new Dimension(150, 50));
        btnMedico.setBackground(Color.WHITE);
        btnMedico.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicoActionPerformed(evt);
            }
        });

        btnGestante = new RoundedButton("Ver dados da paciente", 10);
        btnGestante.setPreferredSize(new Dimension(150, 50));
        btnGestante.setBackground(Color.WHITE);
        btnGestante.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGestante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestanteActionPerformed(evt);
            }
        });

        panFooter.add(lblFiltro);
        panFooter.add(cbFiltro);
        panFooter.add(btnCancelar);
        panFooter.add(btnRelatorio);

        if (usuario instanceof Gestante) {
            panFooter.add(btnMedico);
        } else {
            panFooter.add(btnGestante);
        }

        add(panFooter, BorderLayout.SOUTH);
    }

    private void btnGestanteActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;

        JDialog parentWindow = (JDialog) SwingUtilities.getWindowAncestor(this);
        DlgDadosGestante dialog = new DlgDadosGestante(parentWindow, true, exame.getPaciente().getId());
        dialog.setVisible(true);
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;

        Object[] options = { "Sim", "Não" };
        int option = JOptionPane.showOptionDialog(this,
                "Tem certeza que deseja cancelar este exame?",
                "Confirmar cancelamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (option == JOptionPane.YES_OPTION) {
            try {
                exameController.cancelar(exame.getId());
                cbFilterActionPerformed(null);
                JOptionPane.showMessageDialog(this, "Exame cancelado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return;

    }

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;

        JDialog parentWindow = (JDialog) SwingUtilities.getWindowAncestor(this);
        DlgDadosRelatorio dialog = new DlgDadosRelatorio(parentWindow, true, exame);
        dialog.setVisible(true);

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
        String selectedFilter = (String) cbFiltro.getSelectedItem();

        if (usuario instanceof Medico) {
            if (selectedFilter.equals("Todos")) {
                exameController.atualizarTabelaPorMedico(grdExames, usuario.getId());
            } else {
                exameController.atualizarTabelaPorMedicoEStatus(grdExames, usuario.getId(), selectedFilter);
            }
        } else {
            if (selectedFilter.equals("Todos")) {
                exameController.atualizarTabelaPorGestante(grdExames, usuario.getId());
            } else {
                exameController.atualizarTabelaPorGestanteEStatus(grdExames, usuario.getId(), selectedFilter);
            }
        }

    }

    private void btnMedicoActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;
        if (exame.getMedico() == null) {
            JOptionPane.showMessageDialog(this, "Não há médico cadastrado para esse exame", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = exame.getMedico().getId();
        JDialog parentWindow = (JDialog) SwingUtilities.getWindowAncestor(this);
        DlgDadosMedico dialog = new DlgDadosMedico(parentWindow, true, id);
        dialog.setVisible(true);
    }
}
