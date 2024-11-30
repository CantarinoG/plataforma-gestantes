package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.AuthTemp;
import com.cantarino.souza.view.components.*;

public class DlgConsultas extends JDialog {

    private JPanel panBackground;
    private JPanel panHeader;
    private JLabel lblTitle;
    private JPanel panContent;
    private JTable grdConsultas;
    private JScrollPane scrollPane;
    private JPanel panFooter;
    private JButton btnCadastrar;
    private JButton btnEditar;
    private JButton btnDeletar;
    private JButton btnVisuRelatorio;
    private JButton btnGerenciarRelatorio;

    private Usuario usuario;

    private ConsultaController consultaController;

    public DlgConsultas(JFrame parent, boolean modal) {
        super(parent, modal);
        usuario = AuthTemp.getInstance().getUsuario();
        initComponents();
        consultaController = new ConsultaController();
        consultaController.atualizarTabela(grdConsultas);
    }

    private void initComponents() {
        setTitle("Gerenciador de Consultas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new BorderLayout());
        setContentPane(panBackground);

        panHeader = new JPanel();
        panHeader.setPreferredSize(new Dimension(getWidth(), 80));
        panHeader.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panHeader.setBackground(AppColors.BUTTON_PINK);
        panHeader.setOpaque(true);
        panHeader.setLayout(new GridBagLayout());

        lblTitle = new JLabel("Consultas");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(AppColors.TITLE_BLUE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        panHeader.add(lblTitle, gbc);

        panBackground.add(panHeader, BorderLayout.NORTH);
        panContent = new PanConsultasAgendadas();
        panContent.setLayout(new BorderLayout());
        panContent.setBackground(new Color(255, 255, 255));
        panContent.setOpaque(true);

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
        panContent.add(scrollPane, BorderLayout.CENTER);

        panFooter = new JPanel();
        panFooter.setPreferredSize(new Dimension(getWidth(), 80));
        panFooter.setBackground(AppColors.BUTTON_PINK);
        panFooter.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

        btnCadastrar = new RoundedButton("Cadastrar", 10);
        btnCadastrar.setPreferredSize(new Dimension(150, 50));
        btnCadastrar.setBackground(Color.WHITE);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnEditar = new RoundedButton("Editar", 10);
        btnEditar.setPreferredSize(new Dimension(150, 50));
        btnEditar.setBackground(Color.WHITE);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar = new RoundedButton("Deletar", 10);
        btnDeletar.setPreferredSize(new Dimension(150, 50));
        btnDeletar.setBackground(Color.WHITE);
        btnDeletar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnVisuRelatorio = new RoundedButton("Visualizar Relatório", 10);
        btnVisuRelatorio.setPreferredSize(new Dimension(150, 50));
        btnVisuRelatorio.setBackground(Color.WHITE);
        btnVisuRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVisuRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisuRelatorioActionPerformed(evt);
            }
        });

        panFooter.add(btnCadastrar);
        panFooter.add(btnEditar);
        panFooter.add(btnDeletar);
        panFooter.add(btnVisuRelatorio);

        if (usuario instanceof Admin) {
            btnGerenciarRelatorio = new RoundedButton("Cadastrar/Editar Relatório", 10);
            btnGerenciarRelatorio.setPreferredSize(new Dimension(180, 50));
            btnGerenciarRelatorio.setBackground(Color.WHITE);
            btnGerenciarRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnGerenciarRelatorio.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGerenciarRelatorioActionPerformed(evt);
                }
            });

            panFooter.add(btnGerenciarRelatorio);
        }

        panContent.add(panFooter, BorderLayout.SOUTH);

        panBackground.add(panContent, BorderLayout.CENTER);

    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdConsultas.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdConsultas.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        DlgCadastroConsultas dialog = new DlgCadastroConsultas(this, true);
        dialog.setVisible(true);
        consultaController.atualizarTabela(grdConsultas);

    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;
        id = consulta.getId();
        DlgCadastroConsultas dialog = new DlgCadastroConsultas(this, true, id);
        dialog.setVisible(true);
        consultaController.atualizarTabela(grdConsultas);

    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;
        id = consulta.getId();
        consultaController.excluir(id);
        consultaController.atualizarTabela(grdConsultas);

    }

    private void btnVisuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
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
        } else {
            DlgDadosRelatorio dialog = new DlgDadosRelatorio(this, true, consulta);
            dialog.setVisible(true);
        }

    }

    private void btnGerenciarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("aqui gerenciou carai");

    }

}
