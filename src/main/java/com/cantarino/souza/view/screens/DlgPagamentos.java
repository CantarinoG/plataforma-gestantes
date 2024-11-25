package com.cantarino.souza.view.screens;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.PagamentoController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.AuthTemp;
import com.cantarino.souza.view.components.*;

public class DlgPagamentos extends JDialog {

    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JTable grdPagamento;
    JScrollPane scrollPane;
    JPanel panFooter;
    JButton btnRecibo;
    JButton btnCadastrar;
    JButton btnEditar;
    JButton btnDeletar;

    private Usuario usuario;

    private PagamentoController controller;

    public DlgPagamentos(JFrame parent, boolean modal) {
        super(parent, modal);
        controller = new PagamentoController();
        usuario = AuthTemp.getInstance().getUsuario();
        initComponents();
        controller.atualizarTabela(grdPagamento);
    }

    private void initComponents() {
        setTitle("Gestão de Pagamentos");
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

        lblTitle = new JLabel("Pagamentos");
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
        panContent.add(scrollPane, BorderLayout.CENTER);

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

        panBackground.add(panContent, BorderLayout.CENTER);
    }

    private void btnReciboActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        DlgCadastroPagamentos dialog = new DlgCadastroPagamentos(this, true);
        dialog.setVisible(true);
        controller.atualizarTabela(grdPagamento);
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {
    }

}
