package com.cantarino.souza.view.screens;

import com.cantarino.souza.controller.ExameController;
import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.view.components.*;

import java.awt.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class DlgCadastroExames extends JDialog {
    private JPanel panFundo;
    private JPanel panColuna;
    private JLabel lblTitulo;
    private JPanel panBotoes;
    private JButton btnCadastrarConsulta;
    private JComboBox<String> cbPaciente;
    private JTextArea edtDescricao;
    private JFormattedTextField edtData;
    private JTextField edtValor;
    private JComboBox<String> cbMedico;
    private JPanel panCampoPaciente;
    private JPanel panCampoDescricao;
    private JPanel panCampoData;
    private JPanel panCampoValor;
    private JPanel panCampoMedico;
    private JFormattedTextField edtDataResultado;
    private JTextField edtLaboratorio;
    private JPanel panCampoDataResultado;
    private JPanel panCampoLaboratorio;
    private JPanel panCampoStatus;
    private JComboBox<String> cbStatus;
    private JTextField edtDuracao;
    private JPanel panCampoDuracao;

    private MedicoController medicoController;
    private GestanteController gestanteController;
    private ExameController exameController;

    private Exame atualizando = null;

    public DlgCadastroExames(JDialog parent, boolean modal) { // Construtor sem id para cadastro
        super(parent, modal);

        medicoController = new MedicoController();
        gestanteController = new GestanteController();
        exameController = new ExameController();

        initComponents();
    }

    public DlgCadastroExames(JDialog parent, boolean modal, int id) { // Construtor com id para edição
        super(parent, modal);

        medicoController = new MedicoController();
        gestanteController = new GestanteController();
        exameController = new ExameController();
        atualizando = exameController.buscar(id);

        initComponents();

        edtDescricao.setText(atualizando.getDescricao());
        edtData.setText(atualizando.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        edtValor.setText(String.valueOf(atualizando.getValor()));
        if (atualizando.getDataResultado() != null) {
            edtDataResultado.setText(atualizando.getDataResultado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        edtLaboratorio.setText(atualizando.getLaboratorio());
        edtDuracao.setText(String.valueOf(atualizando.getDuracao()));
        Gestante gestante = atualizando.getPaciente();
        cbPaciente.setSelectedItem(gestante.getId() + " | " + gestante.getNome());
        Medico medico = (Medico) atualizando.getRequisitadoPor();
        if (medico != null) {
            cbMedico.setSelectedItem(medico.getId() + " | " + medico.getNome());
        } else {
            cbMedico.setSelectedItem("Requisição Própria");
        }
        cbStatus.setSelectedItem(atualizando.getStatus());
    }

    private String[] getGestantesOptions() {
        java.util.List<Gestante> gestantes = gestanteController.buscarTodos();
        String[] options = new String[gestantes.size()];
        for (int i = 0; i < gestantes.size(); i++) {
            Gestante gestante = gestantes.get(i);
            options[i] = gestante.getId() + " | " + gestante.getNome();
        }
        return options;
    }

    private String[] getMedicosOptions() {
        java.util.List<Medico> medicos = medicoController.buscarTodos();
        String[] options = new String[medicos.size() + 1];
        options[0] = "Requisição Própria";
        for (int i = 0; i < medicos.size(); i++) {
            Medico medico = medicos.get(i);
            options[i + 1] = medico.getId() + " | " + medico.getNome();
        }
        return options;
    }

    private String[] getStatusOptions() {
        StatusProcedimentos[] values = StatusProcedimentos.values();
        String[] options = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = values[i].getValor();
        }
        return options;
    }

    private void initComponents() {
        setTitle("Cadastro de Consulta");
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

        lblTitulo = new JLabel(atualizando != null ? "Editar Exame" : "Cadastrar Exame");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(30, 0, 10, 0);
        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(atualizando != null ? 9 : 8, 1, 20, 5));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna, gbc);

        cbPaciente = new JComboBox<>(getGestantesOptions());
        cbPaciente.setFont(new Font("Arial", Font.PLAIN, 22));
        cbPaciente.setBackground(AppColors.FIELD_PINK);
        panCampoPaciente = criarTextFieldCustomizada("Paciente", cbPaciente);
        panColuna.add(panCampoPaciente);

        edtDescricao = new JTextArea();
        edtDescricao.setFont(new Font("Arial", Font.PLAIN, 22));
        edtDescricao.setBackground(AppColors.FIELD_PINK);
        edtDescricao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        edtDescricao.setLineWrap(true);
        edtDescricao.setWrapStyleWord(true);
        panCampoDescricao = criarTextFieldCustomizada("Descrição do Exame", edtDescricao);
        panColuna.add(panCampoDescricao);

        try {
            MaskFormatter maskData = new MaskFormatter("##/##/#### ##:##");
            maskData.setPlaceholderCharacter('_');
            edtData = new JFormattedTextField(maskData);
            edtData.setFont(new Font("Arial", Font.PLAIN, 22));
            edtData.setBackground(AppColors.FIELD_PINK);
            edtData.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panCampoData = criarTextFieldCustomizada("Data e Hora", edtData);
            panColuna.add(panCampoData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edtDuracao = new JTextField();
        edtDuracao.setFont(new Font("Arial", Font.PLAIN, 22));
        edtDuracao.setBackground(AppColors.FIELD_PINK);
        edtDuracao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoDuracao = criarTextFieldCustomizada("Duração(minutos)", edtDuracao);
        panColuna.add(panCampoDuracao);

        try {
            MaskFormatter maskDataResultado = new MaskFormatter("##/##/####");
            maskDataResultado.setPlaceholderCharacter('_');
            edtDataResultado = new JFormattedTextField(maskDataResultado);
            edtDataResultado.setFont(new Font("Arial", Font.PLAIN, 22));
            edtDataResultado.setBackground(AppColors.FIELD_PINK);
            edtDataResultado.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panCampoDataResultado = criarTextFieldCustomizada("Previsão Data Resultado(Opcional)", edtDataResultado);
            panColuna.add(panCampoDataResultado);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edtLaboratorio = new JTextField();
        edtLaboratorio.setFont(new Font("Arial", Font.PLAIN, 22));
        edtLaboratorio.setBackground(AppColors.FIELD_PINK);
        edtLaboratorio.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoLaboratorio = criarTextFieldCustomizada("Laboratório", edtLaboratorio);
        panColuna.add(panCampoLaboratorio);

        edtValor = new JTextField();
        edtValor.setFont(new Font("Arial", Font.PLAIN, 22));
        edtValor.setBackground(AppColors.FIELD_PINK);
        edtValor.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoValor = criarTextFieldCustomizada("Valor", edtValor);
        panColuna.add(panCampoValor);

        cbMedico = new JComboBox<>(getMedicosOptions());
        cbMedico.setFont(new Font("Arial", Font.PLAIN, 22));
        cbMedico.setBackground(AppColors.FIELD_PINK);
        panCampoMedico = criarTextFieldCustomizada("Requisitado por:", cbMedico);
        panColuna.add(panCampoMedico);

        if (atualizando != null) {
            cbStatus = new JComboBox<>(getStatusOptions());
            cbStatus.setFont(new Font("Arial", Font.PLAIN, 22));
            cbStatus.setBackground(AppColors.FIELD_PINK);
            panCampoStatus = criarTextFieldCustomizada("Status", cbStatus);
            panColuna.add(panCampoStatus);
        }

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 0.5;
        gbcButton.anchor = GridBagConstraints.NORTH;
        gbcButton.insets = new java.awt.Insets(10, 0, 0, 0);

        panBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panBotoes.setBackground(AppColors.TRANSPARENT);

        btnCadastrarConsulta = new RoundedButton(atualizando != null ? "Editar Exame" : "Cadastrar Exame", 10);
        btnCadastrarConsulta.setPreferredSize(new Dimension(150, 50));
        btnCadastrarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarConsulta.setForeground(Color.WHITE);
        btnCadastrarConsulta.addActionListener(evt -> btnCadastrarConsultaActionPerformed(evt));

        panBotoes.add(btnCadastrarConsulta);
        panFundo.add(panBotoes, gbcButton);
    }

    private JPanel criarTextFieldCustomizada(String hint, JComponent textField) {
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
            Gestante paciente = gestanteController.buscar(pacienteId);

            String selectedMedico = (String) cbMedico.getSelectedItem();
            Medico medico = null;
            if (!selectedMedico.equals("Requisição Própria")) {
                int medicoId = Integer.parseInt(selectedMedico.split(" \\| ")[0]);
                medico = medicoController.buscar(medicoId);
            }

            String dataStr = edtData.getText(); // Formato: dd/MM/yyyy HH:mm
            String[] dateParts = dataStr.split(" ")[0].split("/");
            String[] timeParts = dataStr.split(" ")[1].split(":");

            String formattedData = String.format("%s-%s-%sT%s:%s:00",
                    dateParts[2], // ano
                    dateParts[1], // mês
                    dateParts[0], // dia
                    timeParts[0], // hora
                    timeParts[1] // minuto
            );
            String dataResultadoStr = edtDataResultado.getText(); // Formato: dd/MM/yyyy
            String formattedDataResultado = null;
            if (dataResultadoStr != null && !dataResultadoStr.trim().isEmpty()
                    && !dataResultadoStr.equals("__/__/____")) {
                String[] dataResultadoParts = dataResultadoStr.split("/");
                formattedDataResultado = String.format("%s-%s-%s",
                        dataResultadoParts[2], // ano
                        dataResultadoParts[1], // mês
                        dataResultadoParts[0] // dia
                );
            }

            if (atualizando == null) { // Cadstro de exame
                exameController.salvar(
                        paciente,
                        edtDescricao.getText(),
                        formattedData,
                        edtDuracao.getText(),
                        edtValor.getText(),
                        StatusProcedimentos.AGENDADA.getValor(),
                        null,
                        null,
                        formattedDataResultado,
                        medico,
                        edtLaboratorio.getText());
                dispose();
            } else { // Edição de exame
                exameController.editar(
                        atualizando.getId(),
                        paciente,
                        edtDescricao.getText(),
                        formattedData,
                        edtDuracao.getText(),
                        edtValor.getText(),
                        (String) cbStatus.getSelectedItem(),
                        null,
                        null,
                        formattedDataResultado,
                        medico,
                        edtLaboratorio.getText());
                dispose();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
