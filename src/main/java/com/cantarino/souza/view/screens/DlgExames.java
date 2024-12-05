package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.awt.*;
import java.awt.event.ActionEvent;

import com.cantarino.souza.controller.ExameController;
import com.cantarino.souza.controller.PagamentoController;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.BackgroundPanel;
import com.cantarino.souza.view.components.RoundedButton;

public class DlgExames extends JDialog {

    private JPanel panFundo;
    private JPanel panHeader;
    private JLabel lblTitulo;
    private JPanel panConteudo;
    private JTable grdExames;
    private JScrollPane scrollPane;
    private JPanel panFooter;
    private JButton btnCadastrar;
    private JButton btnEditar;
    private JButton btnDeletar;
    private JButton btnVisuRelatorio;
    private JButton btnGerenciarRelatorio;
    private JButton btnVerPagamento;
    private JLabel lblNome;
    private JTextField edtNome;

    private ExameController exameController;
    private PagamentoController pagamentoController;

    public DlgExames(JFrame parent, boolean modal) {
        super(parent, modal);

        pagamentoController = new PagamentoController();
        exameController = new ExameController();

        initComponents();

        exameController.atualizarTabela(grdExames);
    }

    private void initComponents() {
        setTitle("Gerenciador de Exames");
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

        lblTitulo = new JLabel("Exames");
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
        panConteudo.add(scrollPane, BorderLayout.CENTER);

        panFooter = new JPanel();
        panFooter.setPreferredSize(new Dimension(getWidth(), 80));
        panFooter.setBackground(AppColors.BUTTON_PINK);
        panFooter.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

        lblNome = new JLabel("Nome:");
        edtNome = new JTextField(20);
        edtNome.getDocument().addDocumentListener(new DocumentListener() {
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
        panFooter.add(lblNome);
        panFooter.add(edtNome);

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

        btnGerenciarRelatorio = new RoundedButton("Cadastrar/Editar Relatório", 10);
        btnGerenciarRelatorio.setPreferredSize(new Dimension(180, 50));
        btnGerenciarRelatorio.setBackground(Color.WHITE);
        btnGerenciarRelatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGerenciarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarRelatorioActionPerformed(evt);
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
        panFooter.add(btnGerenciarRelatorio);
        panFooter.add(btnVerPagamento);

        panConteudo.add(panFooter, BorderLayout.SOUTH);

        panFundo.add(panConteudo, BorderLayout.CENTER);

    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdExames.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdExames.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        DlgCadastroExames dialog = new DlgCadastroExames(this, true);
        dialog.setVisible(true);
        exameController.atualizarTabela(grdExames);

    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;

        DlgCadastroExames dialog = new DlgCadastroExames(this, true, exame.getId());
        dialog.setVisible(true);
        exameController.atualizarTabela(grdExames);

    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;

        int option = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir este exame?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            exameController.deletar(exame.getId());
            exameController.atualizarTabela(grdExames);
        }
    }

    private void btnVisuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;

        DlgDadosRelatorio dialog = new DlgDadosRelatorio(this, true, exame);
        dialog.setVisible(true);

    }

    private void btnGerenciarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;

        // Preciso fazer essa verificação
        // direto aqui na view pois afeta
        // diretamente o fluxo de telas. Se
        // a consulta estiver cancelada,
        // não quero nem que vá para a tela
        // de cadastro, ao invés de fazer essa verficiação lá.
        if (exame.getStatus().equals(StatusProcedimentos.CANCELADA.getValor())) {
            JOptionPane.showMessageDialog(this,
                    "Não é possível adicionar ou editar um relatório num procedimento cancelado", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DlgCadastroRelatorio dialog = new DlgCadastroRelatorio(this, true, exame.getId());
        dialog.setVisible(true);
        exameController.atualizarTabela(grdExames);

    }

    private void filterTable() {
        String searchText = edtNome.getText();

        exameController.atualizarTabelaPorNomeGestante(grdExames, searchText);

    }

    private void btnVerPagamentoActionPerformed(ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Exame exame = (Exame) selectedObject;
        Pagamento pagamento = pagamentoController.buscarPorIdProcedimento(exame.getId());

        DlgDadosPagamento dialog = new DlgDadosPagamento(this, true, pagamento);
        dialog.setVisible(true);
    }

}
