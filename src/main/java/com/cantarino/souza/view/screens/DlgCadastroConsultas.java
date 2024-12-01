package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class DlgCadastroConsultas extends JDialog {
    private JPanel panBackground;
    private JPanel panColumn;
    private JLabel lblAction;
    private JPanel panButton;
    private JButton btnCadastrarConsulta;
    private JComboBox<String> cbPaciente;
    private JTextArea txtDescricao;
    private JFormattedTextField txtData;
    private JTextField txtValor;
    private JComboBox<String> cbMedico;
    private JComboBox<String> cbStatus;
    private JPanel panPacienteField;
    private JPanel panDescricaoField;
    private JPanel panDataField;
    private JPanel panValorField;
    private JPanel panMedicoField;
    private JPanel panStatusField;
    private JTextField txtDuracao;
    private JPanel panDuracaoField;

    private MedicoController medicoController;
    private GestanteController gestanteController;
    private ConsultaController consultaController;

    private Consulta atualizando;

    public DlgCadastroConsultas(JDialog parent, boolean modal) {
        super(parent, modal);
        medicoController = new MedicoController();
        gestanteController = new GestanteController();
        consultaController = new ConsultaController();
        atualizando = null;
        initComponents();
    }

    public DlgCadastroConsultas(JDialog parent, boolean modal, int id) {
        super(parent, modal);
        medicoController = new MedicoController();
        gestanteController = new GestanteController();
        consultaController = new ConsultaController();
        atualizando = consultaController.buscarPorId(id);
        initComponents();

        // Fill fields with existing data
        cbPaciente.setSelectedItem(atualizando.getPaciente().getId() + " | " + atualizando.getPaciente().getNome());
        txtDescricao.setText(atualizando.getDescricao());
        txtData.setText(atualizando.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtDuracao.setText(String.valueOf(atualizando.getDuracao()));
        txtValor.setText(String.valueOf(atualizando.getValor()));
        cbMedico.setSelectedItem(atualizando.getMedico().getId() + " | " + atualizando.getMedico().getNome());
        cbStatus.setSelectedItem(atualizando.getStatus());
    }

    private String[] getGestantesOptions() {
        java.util.List<Gestante> gestantes = gestanteController.buscarTodas();
        String[] options = new String[gestantes.size()];
        for (int i = 0; i < gestantes.size(); i++) {
            Gestante gestante = gestantes.get(i);
            options[i] = gestante.getId() + " | " + gestante.getNome();
        }
        return options;
    }

    private String[] getMedicosOptions() {
        java.util.List<Medico> medicos = medicoController.buscarTodas();
        String[] options = new String[medicos.size()];
        for (int i = 0; i < medicos.size(); i++) {
            Medico medico = medicos.get(i);
            options[i] = medico.getId() + " | " + medico.getNome();
        }
        return options;
    }

    private String[] getStatusOptions() {
        StatusProcedimentos[] values = StatusProcedimentos.values();
        String[] options = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = values[i].getValue();
        }
        return options;
    }

    private void initComponents() {
        setTitle("Cadastro de Consulta");
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

        // Título
        lblAction = new JLabel(atualizando != null ? "Editar Consulta" : "Cadastrar Consulta");
        lblAction.setFont(new Font("Arial", Font.BOLD, 32));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        // Configuração para o painel de campos
        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(30, 0, 10, 0);
        panColumn = new JPanel();
        panColumn.setLayout(new GridLayout(atualizando != null ? 7 : 6, 1, 20, 5));
        panColumn.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panColumn, gbc);

        // Campo Paciente
        cbPaciente = new JComboBox<>(getGestantesOptions());
        cbPaciente.setFont(new Font("Arial", Font.PLAIN, 22));
        cbPaciente.setBackground(AppColors.FIELD_PINK);
        panPacienteField = createCustomTextfield("Paciente", cbPaciente);
        panColumn.add(panPacienteField);

        // Campo Descrição da Consulta
        txtDescricao = new JTextArea();
        txtDescricao.setFont(new Font("Arial", Font.PLAIN, 22));
        txtDescricao.setBackground(AppColors.FIELD_PINK);
        txtDescricao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        panDescricaoField = createCustomTextfield("Descrição da Consulta", txtDescricao);
        panColumn.add(panDescricaoField);

        // Campo Data
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/#### ##:##");
            maskData.setPlaceholderCharacter('_');
            txtData = new JFormattedTextField(maskData);
            txtData.setFont(new Font("Arial", Font.PLAIN, 22));
            txtData.setBackground(AppColors.FIELD_PINK);
            txtData.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panDataField = createCustomTextfield("Data e Hora", txtData);
            panColumn.add(panDataField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Campo Duração
        txtDuracao = new JTextField();
        txtDuracao.setFont(new Font("Arial", Font.PLAIN, 22));
        txtDuracao.setBackground(AppColors.FIELD_PINK);
        txtDuracao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panDuracaoField = createCustomTextfield("Duração(minutos)", txtDuracao);
        panColumn.add(panDuracaoField);

        // Campo Valor
        txtValor = new JTextField();
        txtValor.setFont(new Font("Arial", Font.PLAIN, 22));
        txtValor.setBackground(AppColors.FIELD_PINK);
        txtValor.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panValorField = createCustomTextfield("Valor", txtValor);
        panColumn.add(panValorField);

        // Campo Médico
        cbMedico = new JComboBox<>(getMedicosOptions());
        cbMedico.setFont(new Font("Arial", Font.PLAIN, 22));
        cbMedico.setBackground(AppColors.FIELD_PINK);
        panMedicoField = createCustomTextfield("Médico", cbMedico);
        panColumn.add(panMedicoField);

        // Campo Status (apenas quando editando)
        if (atualizando != null) {
            cbStatus = new JComboBox<>(getStatusOptions());
            cbStatus.setFont(new Font("Arial", Font.PLAIN, 22));
            cbStatus.setBackground(AppColors.FIELD_PINK);
            panStatusField = createCustomTextfield("Status", cbStatus);
            panColumn.add(panStatusField);
        }

        // Botão
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 0.5;
        gbcButton.anchor = GridBagConstraints.NORTH;
        gbcButton.insets = new java.awt.Insets(10, 0, 0, 0);

        panButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panButton.setBackground(AppColors.TRANSPARENT);

        btnCadastrarConsulta = new RoundedButton(atualizando != null ? "Editar Consulta" : "Cadastrar Consulta", 10);
        btnCadastrarConsulta.setPreferredSize(new Dimension(150, 50));
        btnCadastrarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarConsulta.setForeground(Color.WHITE);
        btnCadastrarConsulta.addActionListener(evt -> btnCadastrarConsultaActionPerformed(evt));

        panButton.add(btnCadastrarConsulta);
        panBackground.add(panButton, gbcButton);
    }

    private JPanel createCustomTextfield(String hint, JComponent textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        fieldPanel.setPreferredSize(new Dimension(500, 60));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel hintLabel = new JLabel(hint);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldPanel.add(hintLabel, BorderLayout.NORTH);

        if (textField instanceof JTextField) {
            ((JTextField) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        } else if (textField instanceof JTextArea) {
            ((JTextArea) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        } else if (textField instanceof JComboBox) {
            ((JComboBox<?>) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        }

        textField.setBackground(AppColors.FIELD_PINK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void btnCadastrarConsultaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String selectedPaciente = (String) cbPaciente.getSelectedItem();
            int pacienteId = Integer.parseInt(selectedPaciente.split(" \\| ")[0]);
            Gestante paciente = gestanteController.buscarPorId(pacienteId);

            String selectedMedico = (String) cbMedico.getSelectedItem();
            int medicoId = Integer.parseInt(selectedMedico.split(" \\| ")[0]);
            Medico medico = medicoController.buscarPorId(medicoId);

            String dataStr = txtData.getText(); // Format: dd/MM/yyyy HH:mm
            String[] dateParts = dataStr.split(" ")[0].split("/");
            String[] timeParts = dataStr.split(" ")[1].split(":");

            String formattedData = String.format("%s-%s-%sT%s:%s:00",
                    dateParts[2], // year
                    dateParts[1], // month
                    dateParts[0], // day
                    timeParts[0], // hour
                    timeParts[1] // minute
            );

            if (atualizando == null) {
                consultaController.cadastrar(
                        paciente,
                        txtDescricao.getText(),
                        formattedData,
                        txtDuracao.getText(),
                        txtValor.getText(),
                        StatusProcedimentos.AGENDADA.getValue(),
                        null,
                        null,
                        medico,
                        null);
                dispose();
            } else {
                consultaController.atualizar(
                        atualizando.getId(),
                        paciente,
                        txtDescricao.getText(),
                        formattedData,
                        txtDuracao.getText(),
                        txtValor.getText(),
                        (String) cbStatus.getSelectedItem(),
                        null,
                        null,
                        medico,
                        null);
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
