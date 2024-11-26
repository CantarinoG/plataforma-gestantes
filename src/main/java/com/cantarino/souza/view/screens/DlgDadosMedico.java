package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.view.components.*;

import java.awt.*;

import javax.swing.*;

public class DlgDadosMedico extends JDialog {
    JPanel panBackground;
    JPanel panColumn;
    JLabel lblAction;
    JLabel lblNome;
    JLabel lblCrm;
    JPanel panNomeField;
    JPanel panButton;
    JPanel panCRMField;
    JLabel lblEspecializacao;
    JPanel panEspecializacaoField;
    JLabel lblEmail;
    JPanel panEmailField;
    JLabel lblTelefone;
    JPanel panTelefoneField;
    JLabel lblEndereco;
    JPanel panEnderecoField;

    private MedicoController medicoController;
    private Medico medico;

    public DlgDadosMedico(JDialog parent, boolean modal, int id) {
        super(parent, modal);
        medicoController = new MedicoController();
        medico = medicoController.buscarPorId(id);
        initComponents();

        lblCrm.setText(medico.getCrm());
        lblNome.setText(medico.getNome());
        lblEmail.setText(medico.getEmail());
        lblTelefone.setText(medico.getTelefone());
        lblEndereco.setText(medico.getEndereco());
        lblEspecializacao.setText(medico.getEspecializacao());
    }

    private void initComponents() {
        setTitle("Dados do Médico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1620, 930);
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

        lblAction = new JLabel("Dados do Médico(a)");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        gbc.gridy = 1;
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(6, 2, 20, 10));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn, gbc);

        lblCrm = new JLabel();
        lblCrm.setFont(new Font("Arial", Font.PLAIN, 22));
        lblCrm.setBackground(AppColors.FIELD_PINK);
        lblCrm.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCRMField = createCustomLabel("CRM", lblCrm);
        panColumn.add(panCRMField);

        lblNome = new JLabel();
        lblNome.setFont(new Font("Arial", Font.PLAIN, 22));
        lblNome.setBackground(AppColors.FIELD_PINK);
        lblNome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panNomeField = createCustomLabel("Nome", lblNome);
        panColumn.add(panNomeField);

        lblEspecializacao = new JLabel();
        lblEspecializacao.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEspecializacao.setBackground(AppColors.FIELD_PINK);
        lblEspecializacao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEspecializacaoField = createCustomLabel("Especialização", lblEspecializacao);
        panColumn.add(panEspecializacaoField);

        lblEmail = new JLabel();
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEmail.setBackground(AppColors.FIELD_PINK);
        lblEmail.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEmailField = createCustomLabel("Email", lblEmail);
        panColumn.add(panEmailField);

        lblTelefone = new JLabel();
        lblTelefone.setFont(new Font("Arial", Font.PLAIN, 22));
        lblTelefone.setBackground(AppColors.FIELD_PINK);
        lblTelefone.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panTelefoneField = createCustomLabel("Telefone", lblTelefone);
        panColumn.add(panTelefoneField);

        lblEndereco = new JLabel();
        lblEndereco.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEndereco.setBackground(AppColors.FIELD_PINK);
        lblEndereco.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEnderecoField = createCustomLabel("Endereço", lblEndereco);
        panColumn.add(panEnderecoField);
    }

    private JPanel createCustomLabel(String hint, JComponent label) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        fieldPanel.setPreferredSize(new Dimension(300, 60));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel hintLabel = new JLabel(hint);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldPanel.add(hintLabel, BorderLayout.NORTH);

        label.setBackground(AppColors.FIELD_PINK);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(label, BorderLayout.CENTER);

        return fieldPanel;
    }
}