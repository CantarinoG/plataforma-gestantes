package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import com.cantarino.souza.controller.AdminController;
import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.controller.ExameController;
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
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.view.components.*;
import java.awt.event.ActionEvent;

public class DlgCadastroPagamentos extends JDialog {
    JPanel panFundo;
    JPanel panColuna;
    JLabel lblTitulo;
    JPanel panBotao;
    RoundedButton btnCadastrarPagamento;
    JTextField edtValor;
    JComboBox<String> cmbMetodoPagamento;
    JComboBox<String> cmbProcedimento;
    JPanel panValor;
    JPanel panMetodo;
    JPanel panProcedimento;

    PagamentoController pagamentoController;
    ExameController exameController;
    ConsultaController consultaController;
    AutenticacaoController autenticacaoController;
    Pagamento atualizando;

    Usuario usuario = null;

    public DlgCadastroPagamentos(JDialog parent, boolean modal, int id) { // Construtor com id para edição
        super(parent, modal);

        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();
        pagamentoController = new PagamentoController();
        exameController = new ExameController();
        consultaController = new ConsultaController();
        atualizando = pagamentoController.buscarPorId(id);

        initComponents();

        if (atualizando != null) {
            edtValor.setText(String.valueOf(atualizando.getValor()));
            cmbMetodoPagamento.setSelectedItem(atualizando.getMetodoPagamento());
        }
    }

    public DlgCadastroPagamentos(JDialog parent, boolean modal) { // Construtor sem id para criação
        super(parent, modal);

        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();
        pagamentoController = new PagamentoController();
        exameController = new ExameController();
        consultaController = new ConsultaController();

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
                .filter(e -> !e.getStatus().equals(StatusProcedimentos.CANCELADA.getValor()))
                .map(e -> e.getId() + "|Exame|R$ " + e.getValor() + "| " + e.getDescricao())
                .collect(java.util.stream.Collectors.toList());

        java.util.List<String> consultaOptions = consultas.stream()
                .filter(c -> !paidProcedureIds.contains(c.getId()))
                .filter(c -> !c.getStatus().equals(StatusProcedimentos.CANCELADA.getValor()))
                .map(c -> c.getId() + "|Consulta|R$ " + c.getValor() + "| " + c.getDescricao())
                .collect(java.util.stream.Collectors.toList());

        exameOptions.addAll(consultaOptions);
        return exameOptions.toArray(new String[0]);
    }

    private String[] getPagamentoOptions() {
        MetodoPagamento[] values = MetodoPagamento.values();
        String[] options = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = values[i].getValor();
        }
        return options;
    }

    private void initComponents() {
        setTitle("Cadastro de Pagamento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panFundo = new BackgroundPanel("/images/background.png");
        panFundo.setLayout(new GridBagLayout());
        setContentPane(panFundo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        lblTitulo = new JLabel("Cadastrar Pagamento");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(atualizando == null ? 3 : 2, 1, 20, 10));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna, gbc);

        edtValor = new JTextField();
        edtValor.setFont(new Font("Arial", Font.PLAIN, 22));
        edtValor.setBackground(AppColors.FIELD_PINK);
        ((AbstractDocument) edtValor.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        panValor = criarTextFieldsCustomizados("Valor", edtValor);
        panColuna.add(panValor);

        if (atualizando == null) {
            cmbProcedimento = new JComboBox<>(getProcedimentosOptions());
            cmbProcedimento.setFont(new Font("Arial", Font.PLAIN, 22));
            cmbProcedimento.setBackground(AppColors.FIELD_PINK);
            panProcedimento = criarTextFieldsCustomizados("Procedimento", cmbProcedimento);
            panColuna.add(panProcedimento);
        }

        cmbMetodoPagamento = new JComboBox<>(getPagamentoOptions());
        cmbMetodoPagamento.setFont(new Font("Arial", Font.PLAIN, 22));
        cmbMetodoPagamento.setBackground(AppColors.FIELD_PINK);
        panMetodo = criarTextFieldsCustomizados("Método de Pagamento", cmbMetodoPagamento);
        panColuna.add(panMetodo);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 1.0;
        gbcButton.anchor = GridBagConstraints.CENTER;
        gbcButton.insets = new java.awt.Insets(20, 0, 0, 0);

        panBotao = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panBotao.setBackground(AppColors.TRANSPARENT);

        btnCadastrarPagamento = new RoundedButton("Confirmar Pagamento", 10);
        btnCadastrarPagamento.setPreferredSize(new Dimension(150, 50));
        btnCadastrarPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarPagamento.setForeground(Color.WHITE);
        btnCadastrarPagamento.addActionListener(evt -> btnCadastrarPagamentoActionPerformed(evt));

        panBotao.add(btnCadastrarPagamento);
        panFundo.add(panBotao, gbcButton);
    }

    private JPanel criarTextFieldsCustomizados(String hint, JComponent textField) {
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
                registradoPor = new SecretarioController().buscar(usuario.getId());
            } else if (usuario instanceof Admin) {
                registradoPor = new AdminController().buscar(usuario.getId());
            }

            String metodoPagamento = (String) cmbMetodoPagamento.getSelectedItem();
            String valor = edtValor.getText();

            double valorPago = Double.parseDouble(valor);

            if (atualizando != null) { // Atualizando
                double valorProcedimento = atualizando.getProcedimento().getValor();
                if (valorPago < valorProcedimento) {
                    double desconto = valorProcedimento - valorPago;
                    Object[] options = { "Sim", "Não" };
                    int option = JOptionPane.showOptionDialog(this,
                            "O valor informado é menor que o valor do procedimento. Deseja confirmar o desconto de R$"
                                    + desconto + "?",
                            "Confirmar desconto",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

                    if (option != 0) {
                        return;
                    }
                }

                pagamentoController.atualizar(atualizando.getId(), valor, atualizando.getRegistradoPor(),
                        atualizando.getPaciente(), metodoPagamento, atualizando.getProcedimento(), null);
                dispose();
                return;
            }

            String selectedProcedimento = (String) cmbProcedimento.getSelectedItem();
            String[] procedimentoParts = selectedProcedimento.split("\\|");
            int procedimentoId = Integer.parseInt(procedimentoParts[0]);
            String procedimentoTipo = procedimentoParts[1];

            Procedimento procedimento;
            if (procedimentoTipo.equals("Exame")) {
                procedimento = exameController.buscar(procedimentoId);
            } else {
                procedimento = consultaController.buscar(procedimentoId);
            }

            double valorProcedimento = procedimento.getValor();

            if (valorPago < valorProcedimento) {
                double desconto = valorProcedimento - valorPago;
                Object[] options = { "Sim", "Não" };
                int option = JOptionPane.showOptionDialog(this,
                        "O valor informado é menor que o valor do procedimento. Deseja confirmar o desconto de R$"
                                + desconto + "?",
                        "Confirmar desconto",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (option != 0) {
                    return;
                }
            }

            pagamentoController.cadastrar(valor, registradoPor, procedimento.getPaciente(),
                    metodoPagamento, procedimento, null);
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
