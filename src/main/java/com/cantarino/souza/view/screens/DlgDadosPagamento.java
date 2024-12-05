package com.cantarino.souza.view.screens;

import javax.swing.*;
import java.awt.*;

import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.view.components.AppColors;

public class DlgDadosPagamento extends JDialog {

    JPanel panFundo;
    JPanel panColuna;
    JLabel lblTitulo;
    JLabel lblValor;
    JLabel lblMetodoPagamento;
    JLabel lblProcedimento;
    JPanel panValor;
    JPanel panMetodo;
    JPanel panProcedimento;

    public DlgDadosPagamento(JDialog parent, boolean modal, Pagamento pagamento) {
        super(parent, modal);

        if (pagamento == null) {
            initComponentsVazio();
        } else {
            initComponents();
            lblValor.setText(String.valueOf(pagamento.getValor()));
            lblMetodoPagamento.setText(pagamento.getMetodoPagamento());
            lblProcedimento.setText(pagamento.getProcedimento().getDescricao());
        }

    }

    private void initComponents() {
        setTitle("Dados de Pagamento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1620, 930);
        setLocationRelativeTo(null);

        panFundo = new JPanel();
        panFundo.setBackground(AppColors.BUTTON_PINK);
        panFundo.setLayout(new GridBagLayout());
        setContentPane(panFundo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        lblTitulo = new JLabel("Dados de Pagamento");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(3, 1, 20, 10));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna, gbc);

        lblValor = new JLabel();
        lblValor.setFont(new Font("Arial", Font.PLAIN, 22));
        lblValor.setBackground(AppColors.FIELD_PINK);
        lblValor.setOpaque(true);
        panValor = criarCampoCustomizado("Valor", lblValor);
        panColuna.add(panValor);

        lblProcedimento = new JLabel();
        lblProcedimento.setFont(new Font("Arial", Font.PLAIN, 22));
        lblProcedimento.setBackground(AppColors.FIELD_PINK);
        lblProcedimento.setOpaque(true);
        panProcedimento = criarCampoCustomizado("Procedimento", lblProcedimento);
        panColuna.add(panProcedimento);

        lblMetodoPagamento = new JLabel();
        lblMetodoPagamento.setFont(new Font("Arial", Font.PLAIN, 22));
        lblMetodoPagamento.setBackground(AppColors.FIELD_PINK);
        lblMetodoPagamento.setOpaque(true);
        panMetodo = criarCampoCustomizado("Método de Pagamento", lblMetodoPagamento);
        panColuna.add(panMetodo);
    }

    private void initComponentsVazio() {
        setTitle("Dados de Pagamento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1620, 930);
        setLocationRelativeTo(null);

        panFundo = new JPanel();
        panFundo.setBackground(AppColors.BUTTON_PINK);
        panFundo.setLayout(new GridBagLayout());
        setContentPane(panFundo);

        JLabel lblMensagem = new JLabel("Não há pagamento cadastrado");
        lblMensagem.setFont(new Font("Arial", Font.BOLD, 32));
        lblMensagem.setForeground(AppColors.TITLE_BLUE);
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblMensagem, new GridBagConstraints());
    }

    private JPanel criarCampoCustomizado(String hint, JComponent textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        fieldPanel.setPreferredSize(new Dimension(300, 60));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel hintLabel = new JLabel(hint);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldPanel.add(hintLabel, BorderLayout.NORTH);

        textField.setBackground(AppColors.FIELD_PINK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }
}
