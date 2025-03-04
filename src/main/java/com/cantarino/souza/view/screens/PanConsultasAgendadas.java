package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.RoundedButton;

import java.awt.*;

public class PanConsultasAgendadas extends JPanel {

    JTable grdConsultas;
    JScrollPane scrollPane;
    JPanel panFooter;
    JLabel lblFiltro;
    JComboBox<String> cbFiltro;
    JButton btnCancelar;
    JButton btnRelatorio;
    JButton btnMedico;
    JButton btnGestante;
    JButton btnCadastrarRelatorio;

    private ConsultaController consultaController;
    private AutenticacaoController autenticacaoController;

    private Usuario usuario = null;

    public PanConsultasAgendadas() {
        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();
        consultaController = new ConsultaController();

        initComponents();

        if (usuario instanceof Gestante) {
            consultaController.atualizarTabelaPorGestante(grdConsultas, usuario.getId());
        } else {
            consultaController.atualizarTabelaPorMedico(grdConsultas, usuario.getId());
        }

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

        lblFiltro = new JLabel("Filtrar por:");
        cbFiltro = new JComboBox<>(
                new String[] { "Todos", StatusProcedimentos.AGENDADA.getValor(),
                        StatusProcedimentos.CONCLUIDA.getValor(), StatusProcedimentos.CANCELADA.getValor() });
        cbFiltro.addActionListener(new java.awt.event.ActionListener() {
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

        btnGestante = new RoundedButton("Ver dados da paciente", 10);
        btnGestante.setPreferredSize(new Dimension(150, 50));
        btnGestante.setBackground(Color.WHITE);
        btnGestante.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGestante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestanteActionPerformed(evt);
            }
        });

        btnCadastrarRelatorio = new RoundedButton("Cadastrar/Atualizar relatório", 10);
        btnCadastrarRelatorio.setPreferredSize(new Dimension(200, 50));
        btnCadastrarRelatorio.setBackground(Color.WHITE);
        btnCadastrarRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarRelatorioActionPerformed(evt);
            }
        });

        panFooter.add(lblFiltro);
        panFooter.add(cbFiltro);
        panFooter.add(btnCancelar);
        panFooter.add(btnRelatorio);

        if (usuario instanceof Gestante) {
            panFooter.add(btnMedico);
        } else {
            panFooter.add(btnCadastrarRelatorio);
            panFooter.add(btnGestante);
        }

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

    private void btnCadastrarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Consulta consulta = (Consulta) selectedObject;

        if (consulta.getStatus().equals(StatusProcedimentos.CANCELADA.getValor())) {
            JOptionPane.showMessageDialog(this,
                    "Não é possível adicionar ou editar um relatório num procedimento cancelado", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog parentWindow = (JDialog) SwingUtilities.getWindowAncestor(this);
        DlgCadastroRelatorio dialog = new DlgCadastroRelatorio(parentWindow, true, consulta.getId());
        dialog.setVisible(true);
        consultaController.atualizarTabelaPorMedico(grdConsultas, usuario.getId());

    }

    private void btnGestanteActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;

        JDialog parentWindow = (JDialog) SwingUtilities.getWindowAncestor(this);
        DlgDadosGestante dialog = new DlgDadosGestante(parentWindow, true, consulta.getPaciente().getId());
        dialog.setVisible(true);
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;

        Object[] options = { "Sim", "Não" };
        int option = JOptionPane.showOptionDialog(this,
                "Tem certeza que deseja cancelar esta consulta?",
                "Confirmar cancelamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (option == JOptionPane.YES_OPTION) {
            try {
                consultaController.cancelar(consulta.getId());
                cbFilterActionPerformed(null);
                JOptionPane.showMessageDialog(this, "Consulta cancelada com sucesso!", "Sucesso",
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

        Consulta consulta = (Consulta) selectedObject;

        JDialog parentWindow = (JDialog) SwingUtilities.getWindowAncestor(this);
        DlgDadosRelatorio dialog = new DlgDadosRelatorio(parentWindow, true, consulta);
        dialog.setVisible(true);

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
        String selectedFilter = (String) cbFiltro.getSelectedItem();

        if (usuario instanceof Medico) {
            if (selectedFilter.equals("Todos")) {
                consultaController.atualizarTabelaPorMedico(grdConsultas, usuario.getId());
            } else {
                consultaController.atualizarTabelaPorMedicoEStatus(grdConsultas, usuario.getId(), selectedFilter);
            }
        } else {
            if (selectedFilter.equals("Todos")) {
                consultaController.atualizarTabelaPorGestante(grdConsultas, usuario.getId());
            } else {
                consultaController.atualizarTabelaPorGestanteEStatus(grdConsultas, usuario.getId(), selectedFilter);
            }
        }

    }

}
