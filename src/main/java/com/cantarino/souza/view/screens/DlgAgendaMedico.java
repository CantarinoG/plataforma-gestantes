package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.BackgroundPanel;
import com.cantarino.souza.view.components.RoundedButton;

public class DlgAgendaMedico extends JDialog {

    private JPanel panFundo;
    private JPanel panHeader;
    private JLabel lblTitulo;
    private JPanel panConteudo;
    private JTable grdConsultas;
    private JScrollPane scrollPane;
    private JPanel panFooter;
    private JLabel lblFiltro;
    private JComboBox<String> cbFiltro;
    private JButton btnCancelar;
    private JButton btnVerRelatorio;
    private JButton btnCadastrarRelatorio;
    private JButton btnPaciente;

    private ConsultaController consultaController;
    private AutenticacaoController autenticacaoController;

    private Usuario usuario;

    public DlgAgendaMedico(JFrame parent, boolean modal) {
        super(parent, modal);

        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();
        consultaController = new ConsultaController();

        initComponents();

        consultaController.atualizarTabelaPorMedico(grdConsultas, usuario.getId());
    }

    private void initComponents() {
        setTitle("Gestão de Pagamentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panFundo = new BackgroundPanel("/images/background.png");
        panFundo.setLayout(new BorderLayout());
        setContentPane(panFundo);

        panHeader = new JPanel();
        panHeader.setPreferredSize(new Dimension(getWidth(), 80));
        panHeader.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panHeader.setBackground(AppColors.BUTTON_PINK);
        panHeader.setOpaque(true);
        panHeader.setLayout(new GridBagLayout());

        lblTitulo = new JLabel("Minha Agenda");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        panHeader.add(lblTitulo, gbc);

        panFundo.add(panHeader, BorderLayout.NORTH);
        panConteudo = new PanConsultasAgendadas();
        panConteudo.setLayout(new BorderLayout());
        panConteudo.setBackground(new Color(255, 255, 255));
        panConteudo.setOpaque(true);

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
        panConteudo.add(scrollPane, BorderLayout.CENTER);

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
                cbFiltroActionPerformed(evt);
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

        btnVerRelatorio = new RoundedButton("Ver relatório", 10);
        btnVerRelatorio.setPreferredSize(new Dimension(150, 50));
        btnVerRelatorio.setBackground(Color.WHITE);
        btnVerRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerRelatorioActionPerformed(evt);
            }
        });

        btnCadastrarRelatorio = new RoundedButton("Cadastrar/Atualizar Relatório", 10);
        btnCadastrarRelatorio.setPreferredSize(new Dimension(200, 50));
        btnCadastrarRelatorio.setBackground(Color.WHITE);
        btnCadastrarRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarRelatorioActionPerformed(evt);
            }
        });

        btnPaciente = new RoundedButton("Ver Dados do Paciente", 10);
        btnPaciente.setPreferredSize(new Dimension(150, 50));
        btnPaciente.setBackground(Color.WHITE);
        btnPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacienteActionPerformed(evt);
            }
        });

        panFooter.add(lblFiltro);
        panFooter.add(cbFiltro);
        panFooter.add(btnCancelar);
        panFooter.add(btnPaciente);
        panFooter.add(btnVerRelatorio);
        panFooter.add(btnCadastrarRelatorio);

        panConteudo.add(panFooter, BorderLayout.SOUTH);

        panFundo.add(panConteudo, BorderLayout.CENTER);
    }

    private void cbFiltroActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedFilter = (String) cbFiltro.getSelectedItem();
        if (selectedFilter.equals("Todos")) {
            consultaController.atualizarTabelaPorMedico(grdConsultas, usuario.getId());
        } else {
            consultaController.atualizarTabelaPorMedicoEStatus(grdConsultas, usuario.getId(), selectedFilter);
        }
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

        if (consulta.getStatus().equals(StatusProcedimentos.CANCELADA.getValor())) {
            JOptionPane.showMessageDialog(this, "Consulta já cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (consulta.getStatus().equals(StatusProcedimentos.CONCLUIDA.getValor())) {
            JOptionPane.showMessageDialog(this, "Consulta já concluída", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            // Precisei fazer essa verificação direto na view, para conseguir fazer com que,
            // caso não seja válido, já interrompa antes de mostrar o dialog de confirmação.
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
                id = consulta.getId();
                consultaController.cancelar(id);
                cbFiltroActionPerformed(null);

                JOptionPane.showMessageDialog(this, "Consulta cancelada com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return;
        }
    }

    private void btnVerRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
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

        DlgCadastroRelatorio dialog = new DlgCadastroRelatorio(this, true, consulta.getId());
        dialog.setVisible(true);
        consultaController.atualizarTabelaPorMedico(grdConsultas, usuario.getId());

    }

    private void btnPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;

        DlgDadosGestante dialog = new DlgDadosGestante(this, true, consulta.getPaciente().getId());
        dialog.setVisible(true);
    }

}
