package com.cantarino.souza.view.screens;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.view.components.*;

public class DlgPagamentos extends JDialog {

    JPanel panBackground;
    JLabel lblAction;
    JPanel panTable;
    JPanel panOptions;
    JButton btnEmitirRecibo;
    JButton btnRegistrarPagamento;
    JButton btnEditarPagamento;
    JButton btnDeletarPagamento;
    JTable grdPagamentos;
    JScrollPane scrollPane;

    public DlgPagamentos(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestão de Pagamentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new GridBagLayout());
        panBackground.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(panBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        lblAction = new JLabel("Gestão de Pagamentos");
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        grdPagamentos = new JTable();
        grdPagamentos.setModel(new DefaultTableModel(
                new Object[][] {
                        {},
                        {},
                        {},
                        {}
                },
                new String[] {

                }));
        grdPagamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdPagamentosMouseClicked(evt);
            }
        });
        grdPagamentos.setFillsViewportHeight(true);
        grdPagamentos.setBackground(AppColors.FIELD_PINK);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(grdPagamentos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panBackground.add(scrollPane, gbc);

        panOptions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panOptions.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panBackground.add(panOptions, gbc);

        btnEmitirRecibo = new RoundedButton("Emitir Recibo", 50);
        btnEmitirRecibo.setPreferredSize(new Dimension(200, 55));
        btnEmitirRecibo.setMinimumSize(new Dimension(200, 55));
        btnEmitirRecibo.setMaximumSize(new Dimension(200, 55));
        btnEmitirRecibo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEmitirRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirReciboActionPerformed(evt);
            }
        });

        btnRegistrarPagamento = new RoundedButton("Registrar Pagamento", 50);
        btnRegistrarPagamento.setPreferredSize(new Dimension(200, 55));
        btnRegistrarPagamento.setMinimumSize(new Dimension(200, 55));
        btnRegistrarPagamento.setMaximumSize(new Dimension(200, 55));
        btnRegistrarPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPagamentoActionPerformed(evt);
            }
        });

        btnEditarPagamento = new RoundedButton("Editar Pagamento", 50);
        btnEditarPagamento.setPreferredSize(new Dimension(200, 55));
        btnEditarPagamento.setMinimumSize(new Dimension(200, 55));
        btnEditarPagamento.setMaximumSize(new Dimension(200, 55));
        btnEditarPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPagamentoActionPerformed(evt);
            }
        });

        btnDeletarPagamento = new RoundedButton("Deletar Pagamento", 50);
        btnDeletarPagamento.setPreferredSize(new Dimension(200, 55));
        btnDeletarPagamento.setMinimumSize(new Dimension(200, 55));
        btnDeletarPagamento.setMaximumSize(new Dimension(200, 55));
        btnDeletarPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeletarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarPagamentoActionPerformed(evt);
            }
        });

        initUserCoponents();

    }

    private void initUserCoponents() {
        String userType = "GESTANTE";

        if (userType == "GESTANTE") {
            initGestanteComponents();
        } else if (userType == "SECRETARIO") {
            initSecretarioComponents();
        } else if (userType == "ADM") {
            initAdmComponents();
        }
    }

    private void initGestanteComponents() {
        panOptions.add(btnEmitirRecibo);
    }

    private void initSecretarioComponents() {
        panOptions.add(btnEmitirRecibo);
        panOptions.add(btnRegistrarPagamento);
    }

    private void initAdmComponents() {
        panOptions.add(btnEmitirRecibo);
        panOptions.add(btnRegistrarPagamento);
        panOptions.add(btnEditarPagamento);
        panOptions.add(btnDeletarPagamento);
    }

    private void btnEmitirReciboActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de emitir recibo!");
    }

    private void grdPagamentosMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {

        }
    }

    private void btnRegistrarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou em registrar pagamento");
    }

    private void btnEditarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou em editar pagamento");
    }

    private void btnDeletarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou em deletar pagamento");
    }

}
