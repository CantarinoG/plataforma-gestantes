package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import com.cantarino.souza.controller.AdminController;
import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.controller.ExameController;
import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.controller.PagamentoController;
import com.cantarino.souza.controller.SecretarioController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.MetodoPagamento;
import com.cantarino.souza.view.components.*;
import java.awt.event.ActionEvent;

public class DlgCadastroPagamentos extends JDialog {
    JPanel panBackground;
    JPanel panColumn;
    JLabel lblAction;
    JPanel panButton;
    RoundedButton btnCadastrarPagamento;
    JTextField txtValor;
    JComboBox<String> cmbMetodoPagamento;
    JComboBox<String> cmbProcedimento;
    JPanel panValorField;
    JPanel panMetodoField;
    JPanel panProcedimentoField;

    PagamentoController pagamentoController;
    GestanteController gestanteController;
    ExameController exameController;
    ConsultaController consultaController;
    AutenticacaoController autenticacaoController;
    Pagamento atualizando;

    Usuario usuario;

    public DlgCadastroPagamentos(JDialog parent, boolean modal, int id) {
        super(parent, modal);
        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();
        pagamentoController = new PagamentoController();
        gestanteController = new GestanteController();
        exameController = new ExameController();
        consultaController = new ConsultaController();
        atualizando = pagamentoController.buscarPorId(id);
        initComponents();

        if (atualizando != null) {
            txtValor.setText(String.valueOf(atualizando.getValor()));
            cmbMetodoPagamento.setSelectedItem(atualizando.getMetodoPagamento());
        }
    }

    public DlgCadastroPagamentos(JDialog parent, boolean modal) {
        super(parent, modal);
        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();
        pagamentoController = new PagamentoController();
        gestanteController = new GestanteController();
        exameController = new ExameController();
        consultaController = new ConsultaController();
        atualizando = null;
        initComponents();
    }

    private String[] getProcedimentosOptions() {
        java.util.List<Exame> exames = exameController.buscarTodas();
        java.util.List<Consulta> consultas = consultaController.buscarTodas();
        java.util.List<Pagamento> pagamentos = pagamentoController.buscarTodas();

        java.util.Set<Integer> paidProcedureIds = pagamentos.stream()
                .map(p -> p.getProcedimento().getId())
                .collect(java.util.stream.Collectors.toSet());

        java.util.List<String> exameOptions = exames.stream()
                .filter(e -> !paidProcedureIds.contains(e.getId()))
                .map(e -> e.getId() + "|Exame|R$ " + e.getValor() + "| " + e.getDescricao())
                .collect(java.util.stream.Collectors.toList());

        java.util.List<String> consultaOptions = consultas.stream()
                .filter(c -> !paidProcedureIds.contains(c.getId()))
                .map(c -> c.getId() + "|Consulta|R$ " + c.getValor() + "| " + c.getDescricao())
                .collect(java.util.stream.Collectors.toList());

        exameOptions.addAll(consultaOptions);
        return exameOptions.toArray(new String[0]);
    }

    private String[] getPagamentoOptions() {
        MetodoPagamento[] values = MetodoPagamento.values();
        String[] options = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = values[i].getValue();
        }
        return options;
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
        panColumn.setLayout(new GridLayout(atualizando == null ? 3 : 2, 1, 20, 10));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn, gbc);

        txtValor = new JTextField();
        txtValor.setFont(new Font("Arial", Font.PLAIN, 22));
        txtValor.setBackground(AppColors.FIELD_PINK);
        ((AbstractDocument) txtValor.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        panValorField = createCustomTextfield("Valor", txtValor);
        panColumn.add(panValorField);

        if (atualizando == null) {
            cmbProcedimento = new JComboBox<>(getProcedimentosOptions());
            cmbProcedimento.setFont(new Font("Arial", Font.PLAIN, 22));
            cmbProcedimento.setBackground(AppColors.FIELD_PINK);
            panProcedimentoField = createCustomTextfield("Procedimento", cmbProcedimento);
            panColumn.add(panProcedimentoField);
        }

        cmbMetodoPagamento = new JComboBox<>(getPagamentoOptions());
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
        try {
            Usuario registradoPor = new Usuario();
            if (usuario instanceof Secretario) {
                registradoPor = new SecretarioController().buscarPorId(usuario.getId());
            } else if (usuario instanceof Admin) {
                registradoPor = new AdminController().buscarPorId(usuario.getId());
            }

            String metodoPagamento = (String) cmbMetodoPagamento.getSelectedItem();
            String valor = txtValor.getText();

            if (atualizando == null) {
                String selectedProcedimento = (String) cmbProcedimento.getSelectedItem();
                String[] procedimentoParts = selectedProcedimento.split("\\|");
                int procedimentoId = Integer.parseInt(procedimentoParts[0]);
                String procedimentoTipo = procedimentoParts[1];
                Procedimento procedimento = new Procedimento();
                if (procedimentoTipo.equals("Exame")) {
                    procedimento = exameController.buscarPorId(procedimentoId);
                } else if (procedimentoTipo.equals("Consulta")) {
                    procedimento = consultaController.buscarPorId(procedimentoId);
                }

                pagamentoController.cadastrar(valor, registradoPor, procedimento.getPaciente(), metodoPagamento,
                        procedimento, null);

            } else {
                pagamentoController.atualizar(atualizando.getId(), valor, atualizando.getRegistradoPor(),
                        atualizando.getPaciente(),
                        metodoPagamento, atualizando.getProcedimento(), null);
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
        return text.matches("\\d*\\.?\\d*");
    }
}
