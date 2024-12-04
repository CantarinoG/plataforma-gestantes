package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.view.components.*;

import java.awt.*;

import javax.swing.*;

public class DlgDadosMedico extends JDialog {
    JPanel panFundo;
    JPanel panColuna;
    JLabel lblTitulo;
    JLabel lblNome;
    JLabel lblCrm;
    JPanel panNome;
    JPanel panCRM;
    JLabel lblEspecializacao;
    JPanel panEspecializacao;
    JLabel lblEmail;
    JPanel panEmail;
    JLabel lblTelefone;
    JPanel panTelefone;
    JLabel lblEndereco;
    JPanel panEndereco;

    private MedicoController medicoController;
    private Medico medico;

    public DlgDadosMedico(JDialog parent, boolean modal, int id) {
        super(parent, modal);

        medicoController = new MedicoController();
        medico = medicoController.buscar(id);

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

        lblTitulo = new JLabel("Dados do Médico(a)");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(6, 2, 20, 10));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna, gbc);

        lblCrm = new JLabel();
        lblCrm.setFont(new Font("Arial", Font.PLAIN, 22));
        lblCrm.setBackground(AppColors.FIELD_PINK);
        lblCrm.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCRM = criarCampoCustomizado("CRM", lblCrm);
        panColuna.add(panCRM);

        lblNome = new JLabel();
        lblNome.setFont(new Font("Arial", Font.PLAIN, 22));
        lblNome.setBackground(AppColors.FIELD_PINK);
        lblNome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panNome = criarCampoCustomizado("Nome", lblNome);
        panColuna.add(panNome);

        lblEspecializacao = new JLabel();
        lblEspecializacao.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEspecializacao.setBackground(AppColors.FIELD_PINK);
        lblEspecializacao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEspecializacao = criarCampoCustomizado("Especialização", lblEspecializacao);
        panColuna.add(panEspecializacao);

        lblEmail = new JLabel();
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEmail.setBackground(AppColors.FIELD_PINK);
        lblEmail.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEmail = criarCampoCustomizado("Email", lblEmail);
        panColuna.add(panEmail);

        lblTelefone = new JLabel();
        lblTelefone.setFont(new Font("Arial", Font.PLAIN, 22));
        lblTelefone.setBackground(AppColors.FIELD_PINK);
        lblTelefone.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panTelefone = criarCampoCustomizado("Telefone", lblTelefone);
        panColuna.add(panTelefone);

        lblEndereco = new JLabel();
        lblEndereco.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEndereco.setBackground(AppColors.FIELD_PINK);
        lblEndereco.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEndereco = criarCampoCustomizado("Endereço", lblEndereco);
        panColuna.add(panEndereco);
    }

    private JPanel criarCampoCustomizado(String hint, JComponent label) {
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