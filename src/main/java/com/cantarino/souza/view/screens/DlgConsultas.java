package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.controller.PagamentoController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.model.enums.TipoUsuario;
import com.cantarino.souza.view.components.*;

public class DlgConsultas extends JDialog {

    private JPanel panFundo;
    private JPanel panHeader;
    private JLabel lblTitulo;
    private JPanel panConteudo;
    private JTable grdConsultas;
    private JScrollPane scrollPane;
    private JPanel panFooter;
    private JButton btnCadastrar;
    private JButton btnEditar;
    private JButton btnDeletar;
    private JButton btnVisuRelatorio;
    private JButton btnGerenciarRelatorio;
    private JButton btnVerPagamento;
    private JLabel lblFiltro;
    private JComboBox<String> cmbFiltro;
    private JTextField edtFiltro;

    private Usuario usuario = null;

    private ConsultaController consultaController;
    private PagamentoController pagamentoController;
    private AutenticacaoController autenticacaoController;

    public DlgConsultas(JFrame parent, boolean modal) {
        super(parent, modal);

        autenticacaoController = new AutenticacaoController();
        pagamentoController = new PagamentoController();
        usuario = autenticacaoController.getUsuario();
        consultaController = new ConsultaController();

        initComponents();

        consultaController.atualizarTabela(grdConsultas);
    }

    private void initComponents() {
        setTitle("Gerenciador de Consultas");
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

        lblTitulo = new JLabel("Consultas");
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
        lblFiltro.setFont(new Font("Arial", Font.PLAIN, 14));

        String[] userTypes = { TipoUsuario.GESTANTE.getValor(), TipoUsuario.MEDICO.getValor() };
        cmbFiltro = new JComboBox<String>(userTypes);
        cmbFiltro.setPreferredSize(new Dimension(150, 30));

        edtFiltro = new JTextField();
        edtFiltro.setPreferredSize(new Dimension(150, 30));
        edtFiltro.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });

        panFooter.add(lblFiltro);
        panFooter.add(cmbFiltro);
        panFooter.add(edtFiltro);

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

        btnVerPagamento = new RoundedButton("Ver detalhes do pagamento", 10);
        btnVerPagamento.setPreferredSize(new Dimension(180, 50));
        btnVerPagamento.setBackground(Color.WHITE);
        btnVerPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPagamentoActionPerformed(evt);
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
            panFooter.add(btnVerPagamento);
        }

        panConteudo.add(panFooter, BorderLayout.SOUTH);

        panFundo.add(panConteudo, BorderLayout.CENTER);

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
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;

        DlgCadastroConsultas dialog = new DlgCadastroConsultas(this, true, consulta.getId());
        dialog.setVisible(true);
        consultaController.atualizarTabela(grdConsultas);

    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {

        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;

        Object[] options = { "Sim", "Não" };
        int option = JOptionPane.showOptionDialog(this,
                "Tem certeza que deseja excluir esta consulta?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (option == JOptionPane.YES_OPTION) {
            consultaController.deletar(consulta.getId());
            consultaController.atualizarTabela(grdConsultas);
        }
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
        consultaController.atualizarTabela(grdConsultas);
    }

    private void btnVerPagamentoActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = (Consulta) selectedObject;
        Pagamento pagamento = pagamentoController.buscarPorIdProcedimento(consulta.getId());
        if (pagamento == null) {
            JOptionPane.showMessageDialog(this, "Não há nenhum pagamento registrado para esse procedimento", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DlgDadosPagamento dialog = new DlgDadosPagamento(this, true, pagamento);
        dialog.setVisible(true);

    }

    private void filterTable() {
        String searchText = edtFiltro.getText();

        String filterType = (String) cmbFiltro.getSelectedItem();

        if (filterType.equals(TipoUsuario.GESTANTE.getValor())) {
            consultaController.atualizarTabelaPorNomeGestante(grdConsultas, searchText);
        } else if (filterType.equals(TipoUsuario.MEDICO.getValor())) {
            consultaController.atualizarTabelaPorNomeMedico(grdConsultas, searchText);
        }

    }

}
