package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import com.cantarino.souza.view.components.*;
import java.awt.event.ActionEvent;

public class DlgCadastroPagamentos extends JDialog {
    JPanel panBackground;
    JPanel panColumn;
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

    public DlgCadastroPagamentos(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Pagamento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new GridBagLayout());
        setContentPane(panBackground);

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
        panBackground.add(lblAction, gbc);

        gbc.gridy = 1;
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(4, 1, 20, 10));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn, gbc);

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

        btnCadastrarPagamento = new RoundedButton("Confirmar Pagamento", 10);
        btnCadastrarPagamento.setPreferredSize(new Dimension(150, 50));
        btnCadastrarPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarPagamento.setForeground(Color.WHITE);
        btnCadastrarPagamento.addActionListener(evt -> btnCadastrarPagamentoActionPerformed(evt));

        panButton.add(btnCadastrarPagamento);
        panBackground.add(panButton, gbcButton);
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
