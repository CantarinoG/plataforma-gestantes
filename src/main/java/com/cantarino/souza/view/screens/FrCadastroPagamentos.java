package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import com.cantarino.souza.view.components.*;
import java.awt.event.ActionEvent;

public class FrCadastroPagamentos extends JFrame {
    JPanel panBackground;
    JPanel panColumn;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JLabel lblAction;
    JPanel panButton;
    RoundedButton btnCadastrarPagamento;
    JTextField txtValor;
    JTextField txtPaciente;
    JComboBox<String> cmbStatus;
    JComboBox<String> cmbMetodoPagamento;
    JPanel panValorField;
    JPanel panPacienteField;
    JPanel panStatusField;
    JPanel panMetodoField;

    public FrCadastroPagamentos() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Cadastrar Pagamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new BorderLayout());
        setContentPane(panBackground);

        panHeader = new JPanel();
        panHeader.setLayout(new BorderLayout());
        panHeader.setBackground(AppColors.DARKER);
        panHeader.setPreferredSize(new Dimension(getWidth(), 74));
        panHeader.setBorder(BorderFactory.createEmptyBorder(15, 64, 15, 64));
        panBackground.add(panHeader, BorderLayout.NORTH);

        lblTitle = new JLabel("BemGestar");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        panHeader.add(lblTitle, BorderLayout.WEST);

        panContent = new JPanel();
        panContent.setLayout(new GridBagLayout());
        panContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panContent.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panContent, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        lblAction = new JLabel("Cadastrar Pagamento");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panContent.add(lblAction, gbc);

        gbc.gridy = 1;
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(4, 1, 20, 10));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panContent.add(panColumn, gbc);

        txtValor = new JTextField();
        txtValor.setFont(new Font("Arial", Font.PLAIN, 22));
        txtValor.setBackground(AppColors.FIELD_PINK);
        ((AbstractDocument) txtValor.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        panValorField = createCustomTextfield("Valor", txtValor);
        panColumn.add(panValorField);

        txtPaciente = new JTextField();
        txtPaciente.setFont(new Font("Arial", Font.PLAIN, 22));
        txtPaciente.setBackground(AppColors.FIELD_PINK);
        panPacienteField = createCustomTextfield("Paciente", txtPaciente);
        panColumn.add(panPacienteField);

        cmbStatus = new JComboBox<>(new String[] { "Ativo", "Inativo" });
        cmbStatus.setFont(new Font("Arial", Font.PLAIN, 22));
        cmbStatus.setBackground(AppColors.FIELD_PINK);
        panStatusField = createCustomTextfield("Status", cmbStatus);
        panColumn.add(panStatusField);

        cmbMetodoPagamento = new JComboBox<>(new String[] { "Cartão", "Dinheiro", "Transferência Pix" });
        cmbMetodoPagamento.setFont(new Font("Arial", Font.PLAIN, 22));
        cmbMetodoPagamento.setBackground(AppColors.FIELD_PINK);
        panMetodoField = createCustomTextfield("Método de Pagamento", cmbMetodoPagamento);
        panColumn.add(panMetodoField);

        // Botão
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 1.0;
        gbcButton.anchor = GridBagConstraints.CENTER;
        gbcButton.insets = new java.awt.Insets(20, 0, 0, 0);

        panButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panButton.setBackground(AppColors.TRANSPARENT);

        btnCadastrarPagamento = new RoundedButton("Confirmar Pagamento", 50);
        btnCadastrarPagamento.setBackground(AppColors.BUTTON_PINK);
        btnCadastrarPagamento.setFont(new Font("Arial", Font.BOLD, 15));
        btnCadastrarPagamento.setFocusPainted(false);
        btnCadastrarPagamento.setBorderPainted(false);
        btnCadastrarPagamento.setPreferredSize(new Dimension(200, 55));
        btnCadastrarPagamento.setOpaque(true);
        btnCadastrarPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarPagamento.addActionListener(evt -> btnCadastrarPagamentoActionPerformed(evt));

        panButton.add(btnCadastrarPagamento);
        panContent.add(panButton, gbcButton);
    }

    private JPanel createCustomTextfield(String hint, JComponent textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        fieldPanel.setPreferredSize(new Dimension(300, 60));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel hintLabel = new JLabel(hint);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldPanel.add(hintLabel, BorderLayout.NORTH);

        if (textField instanceof JTextField) {
            ((JTextField) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        } else if (textField instanceof JComboBox) {
            ((JComboBox<?>) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        }

        textField.setBackground(AppColors.FIELD_PINK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void btnCadastrarPagamentoActionPerformed(ActionEvent evt) {

        System.out.println("Clicou em Cadastrar Pagamento");
    }
}

class NumericDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (isNumeric(string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (isNumeric(text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isNumeric(String text) {
        return text.matches("\\d*");
    }
}
