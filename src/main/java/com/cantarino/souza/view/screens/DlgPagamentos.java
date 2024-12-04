package com.cantarino.souza.view.screens;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.PagamentoController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.components.*;

public class DlgPagamentos extends JDialog {

    JPanel panFundo;
    JPanel panHeader;
    JLabel lblTitulo;
    JPanel panConteudo;
    JTable grdPagamento;
    JScrollPane scrollPane;
    JPanel panFooter;
    JButton btnRecibo;
    JButton btnCadastrar;
    JButton btnEditar;
    JButton btnDeletar;

    private Usuario usuario = null;

    private PagamentoController controller;
    private AutenticacaoController autenticacaoController;

    public DlgPagamentos(JFrame parent, boolean modal) {
        super(parent, modal);

        controller = new PagamentoController();
        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();

        initComponents();

        atualizarTabela();
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

        lblTitulo = new JLabel("Pagamentos");
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

        grdPagamento = new JTable();
        grdPagamento.setModel(new DefaultTableModel(
                new Object[][] {
                        {},
                        {},
                        {},
                        {}
                },
                new String[] {
                }));
        scrollPane = new JScrollPane(grdPagamento);
        panConteudo.add(scrollPane, BorderLayout.CENTER);

        panFooter = new JPanel();
        panFooter.setPreferredSize(new Dimension(getWidth(), 80));
        panFooter.setBackground(AppColors.BUTTON_PINK);
        panFooter.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

        btnRecibo = new RoundedButton("Emitir Recibo", 10);
        btnRecibo.setPreferredSize(new Dimension(150, 50));
        btnRecibo.setBackground(Color.WHITE);
        btnRecibo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReciboActionPerformed(evt);
            }
        });

        btnCadastrar = new RoundedButton("Cadastrar Pagamento", 10);
        btnCadastrar.setPreferredSize(new Dimension(150, 50));
        btnCadastrar.setBackground(Color.WHITE);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnEditar = new RoundedButton("Editar Pagamento", 10);
        btnEditar.setPreferredSize(new Dimension(150, 50));
        btnEditar.setBackground(Color.WHITE);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar = new RoundedButton("Deletar Pagamento", 10);
        btnDeletar.setPreferredSize(new Dimension(150, 50));
        btnDeletar.setBackground(Color.WHITE);
        btnDeletar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        if (usuario instanceof Gestante) {
            panFooter.add(btnRecibo);
        } else if (usuario instanceof Secretario) {
            panFooter.add(btnRecibo);
            panFooter.add(btnCadastrar);
        } else if (usuario instanceof Admin) {
            panFooter.add(btnRecibo);
            panFooter.add(btnCadastrar);
            panFooter.add(btnEditar);
            panFooter.add(btnDeletar);
        }

        add(panFooter, BorderLayout.SOUTH);

        panFundo.add(panConteudo, BorderLayout.CENTER);
    }

    private void atualizarTabela() {
        if (usuario instanceof Gestante) {
            controller.filtrarTabelaPorIdPaciente(grdPagamento, usuario.getId());
        } else {
            controller.atualizarTabela(grdPagamento);
        }
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdPagamento.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdPagamento.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void btnReciboActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Selecione um pagamento para gerar o recibo", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Pagamento pagamento = (Pagamento) selectedObject;

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Recibo");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();
                controller.gerarRecibo(selectedPath, pagamento.getId());
            }
            JOptionPane.showMessageDialog(this, "Recibo Gerado com Sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar recibo: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        DlgCadastroPagamentos dialog = new DlgCadastroPagamentos(this, true);
        dialog.setVisible(true);
        atualizarTabela();
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pagamento pagamento = (Pagamento) selectedObject;

        DlgCadastroPagamentos dialog = new DlgCadastroPagamentos(null, true, pagamento.getId());
        dialog.setVisible(true);
        atualizarTabela();
    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pagamento pagamento = (Pagamento) selectedObject;

        Object[] options = { "Sim", "Não" };
        int option = JOptionPane.showOptionDialog(this,
                "Tem certeza que deseja excluir este pagamento?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (option == JOptionPane.YES_OPTION) {
            controller.excluir(pagamento.getId());
            atualizarTabela();
        }
    }

}
