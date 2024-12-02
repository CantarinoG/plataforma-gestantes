package com.cantarino.souza.view.screens;

import javax.swing.*;
import java.awt.*;

import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.view.components.AppColors;

public class DlgDadosPagamento extends JDialog {

    JPanel panBackground;
    JPanel panColumn;
    JLabel lblAction;
    JPanel panButton;
    JLabel lblValor;
    JLabel lblMetodoPagamento;
    JLabel lblProcedimento;
    JPanel panValorField;
    JPanel panMetodoField;
    JPanel panProcedimentoField;

    public DlgDadosPagamento(JDialog parent, boolean modal, Pagamento pagamento) {
        super(parent, modal);
        initComponents();

        lblValor.setText(String.valueOf(pagamento.getValor()));
        lblMetodoPagamento.setText(pagamento.getMetodoPagamento());
        lblProcedimento.setText(pagamento.getProcedimento().getDescricao());
    }

    private void initComponents() {
        setTitle("Dados de Pagamento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1620, 930);
        setLocationRelativeTo(null);

        panBackground = new JPanel();
        panBackground.setBackground(AppColors.BUTTON_PINK);
        panBackground.setLayout(new GridBagLayout());
        setContentPane(panBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        lblAction = new JLabel("Dados de Pagamento");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        gbc.gridy = 1;
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(3, 1, 20, 10));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn, gbc);

        lblValor = new JLabel();
        lblValor.setFont(new Font("Arial", Font.PLAIN, 22));
        lblValor.setBackground(AppColors.FIELD_PINK);
        lblValor.setOpaque(true);
        panValorField = createCustomLabelField("Valor", lblValor);
        panColumn.add(panValorField);

        lblProcedimento = new JLabel();
        lblProcedimento.setFont(new Font("Arial", Font.PLAIN, 22));
        lblProcedimento.setBackground(AppColors.FIELD_PINK);
        lblProcedimento.setOpaque(true);
        panProcedimentoField = createCustomLabelField("Procedimento", lblProcedimento);
        panColumn.add(panProcedimentoField);

        lblMetodoPagamento = new JLabel();
        lblMetodoPagamento.setFont(new Font("Arial", Font.PLAIN, 22));
        lblMetodoPagamento.setBackground(AppColors.FIELD_PINK);
        lblMetodoPagamento.setOpaque(true);
        panMetodoField = createCustomLabelField("Método de Pagamento", lblMetodoPagamento);
        panColumn.add(panMetodoField);
    }

    private JPanel createCustomLabelField(String hint, JComponent textField) {
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
