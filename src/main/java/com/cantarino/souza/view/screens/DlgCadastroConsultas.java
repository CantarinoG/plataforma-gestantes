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
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class DlgCadastroConsultas extends JDialog {
    private JPanel panFundo;
    private JPanel panColuna;
    private JLabel lblTitulo;
    private JPanel panBotao;
    private JButton btnCadastrarConsulta;
    private JComboBox<String> cbPaciente;
    private JTextArea edtDescricao;
    private JFormattedTextField edtData;
    private JTextField edtValor;
    private JComboBox<String> cbMedico;
    private JComboBox<String> cbStatus;
    private JPanel panCampoPaciente;
    private JPanel panCampoDescricao;
    private JPanel panCampoData;
    private JPanel panCampoValor;
    private JPanel panCampoMedico;
    private JPanel panCampoStatus;
    private JTextField edtDuracao;
    private JPanel panCampoDuracao;
    private JComboBox<String> cbRetorno;
    private JPanel panCampoRetorno;

    private MedicoController medicoController;
    private GestanteController gestanteController;
    private ConsultaController consultaController;

    private Consulta atualizando = null;

    public DlgCadastroConsultas(JDialog parent, boolean modal) { // Construtor sem id para cadastro
        super(parent, modal);

        medicoController = new MedicoController();
        gestanteController = new GestanteController();
        consultaController = new ConsultaController();

        initComponents();
    }

    public DlgCadastroConsultas(JDialog parent, boolean modal, int id) { // Construtor com id para edição
        super(parent, modal);

        medicoController = new MedicoController();
        gestanteController = new GestanteController();
        consultaController = new ConsultaController();
        atualizando = consultaController.buscar(id);

        initComponents();

        cbPaciente.setSelectedItem(atualizando.getPaciente().getId() + " | " + atualizando.getPaciente().getNome());
        edtDescricao.setText(atualizando.getDescricao());
        edtData.setText(atualizando.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        edtDuracao.setText(String.valueOf(atualizando.getDuracao()));
        edtValor.setText(String.valueOf(atualizando.getValor()));
        cbMedico.setSelectedItem(atualizando.getMedico().getId() + " | " + atualizando.getMedico().getNome());
        cbStatus.setSelectedItem(atualizando.getStatus());
        if (atualizando.getRetorno() != null) {
            cbRetorno.setSelectedItem(
                    atualizando.getRetorno().getId() + " | " + atualizando.getRetorno().getDescricao());
        }
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
            options[i] = values[i].getValor();
        }
        return options;
    }

    private String[] getConsultasOptions(int idPaciente, int idMedico) {
        java.util.List<Consulta> consultas = consultaController.buscarTodas();
        java.util.List<Consulta> filteredConsultas = consultas.stream()
                .filter(consulta -> (atualizando == null || consulta.getId() != atualizando.getId())
                        && consulta.getPaciente().getId() == idPaciente
                        && consulta.getMedico().getId() == idMedico
                        && consulta.getStatus().equals(StatusProcedimentos.CONCLUIDA.getValor()))
                .collect(java.util.stream.Collectors.toList());

        String[] options = new String[filteredConsultas.size() + 1];
        options[0] = "Nenhuma";
        for (int i = 0; i < filteredConsultas.size(); i++) {
            Consulta consulta = filteredConsultas.get(i);
            options[i + 1] = consulta.getId() + " | " + consulta.getDescricao();
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

        lblTitulo = new JLabel(atualizando != null ? "Editar Consulta" : "Cadastrar Consulta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(30, 0, 10, 0);
        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(atualizando != null ? 8 : 7, 1, 20, 5));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna, gbc);

        cbPaciente = new JComboBox<>(getGestantesOptions());
        cbPaciente.setFont(new Font("Arial", Font.PLAIN, 22));
        cbPaciente.setBackground(AppColors.FIELD_PINK);
        cbPaciente.addActionListener(evt -> {
            cbPacienteActionPerformed(evt);
        });
        panCampoPaciente = criarTextFieldCustomizado("Paciente", cbPaciente);
        panColuna.add(panCampoPaciente);

        edtDescricao = new JTextArea();
        edtDescricao.setFont(new Font("Arial", Font.PLAIN, 22));
        edtDescricao.setBackground(AppColors.FIELD_PINK);
        edtDescricao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        edtDescricao.setLineWrap(true);
        edtDescricao.setWrapStyleWord(true);
        panCampoDescricao = criarTextFieldCustomizado("Descrição da Consulta", edtDescricao);
        panColuna.add(panCampoDescricao);

        try {
            MaskFormatter maskData = new MaskFormatter("##/##/#### ##:##");
            maskData.setPlaceholderCharacter('_');
            edtData = new JFormattedTextField(maskData);
            edtData.setFont(new Font("Arial", Font.PLAIN, 22));
            edtData.setBackground(AppColors.FIELD_PINK);
            edtData.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panCampoData = criarTextFieldCustomizado("Data e Hora", edtData);
            panColuna.add(panCampoData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edtDuracao = new JTextField();
        edtDuracao.setFont(new Font("Arial", Font.PLAIN, 22));
        edtDuracao.setBackground(AppColors.FIELD_PINK);
        edtDuracao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoDuracao = criarTextFieldCustomizado("Duração(minutos)", edtDuracao);
        panColuna.add(panCampoDuracao);

        edtValor = new JTextField();
        edtValor.setFont(new Font("Arial", Font.PLAIN, 22));
        edtValor.setBackground(AppColors.FIELD_PINK);
        edtValor.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCampoValor = criarTextFieldCustomizado("Valor", edtValor);
        panColuna.add(panCampoValor);

        cbMedico = new JComboBox<>(getMedicosOptions());
        cbMedico.setFont(new Font("Arial", Font.PLAIN, 22));
        cbMedico.setBackground(AppColors.FIELD_PINK);
        cbPaciente.addActionListener(evt -> {
            cbMedicoActionPerformed(evt);
        });
        panCampoMedico = criarTextFieldCustomizado("Médico", cbMedico);
        panColuna.add(panCampoMedico);

        cbRetorno = new JComboBox<>(getConsultasOptions(0, 0));
        cbRetorno.setFont(new Font("Arial", Font.PLAIN, 22));
        cbRetorno.setBackground(AppColors.FIELD_PINK);
        panCampoRetorno = criarTextFieldCustomizado("Retorno da Consulta", cbRetorno);
        panColuna.add(panCampoRetorno);

        if (atualizando != null) {
            cbStatus = new JComboBox<>(getStatusOptions());
            cbStatus.setFont(new Font("Arial", Font.PLAIN, 22));
            cbStatus.setBackground(AppColors.FIELD_PINK);
            panCampoStatus = criarTextFieldCustomizado("Status", cbStatus);
            panColuna.add(panCampoStatus);
        }

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 2;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 0.5;
        gbcButton.anchor = GridBagConstraints.NORTH;
        gbcButton.insets = new java.awt.Insets(10, 0, 0, 0);

        panBotao = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panBotao.setBackground(AppColors.TRANSPARENT);

        btnCadastrarConsulta = new RoundedButton(atualizando != null ? "Editar Consulta" : "Cadastrar Consulta", 10);
        btnCadastrarConsulta.setPreferredSize(new Dimension(150, 50));
        btnCadastrarConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarConsulta.setForeground(Color.WHITE);
        btnCadastrarConsulta.addActionListener(evt -> btnCadastrarConsultaActionPerformed(evt));

        panBotao.add(btnCadastrarConsulta);
        panFundo.add(panBotao, gbcButton);
    }

    private JPanel criarTextFieldCustomizado(String hint, JComponent textField) {
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
            int medicoId = Integer.parseInt(selectedMedico.split(" \\| ")[0]);
            Medico medico = medicoController.buscar(medicoId);

            String selectedRetorno = (String) cbRetorno.getSelectedItem();
            Consulta retorno = null;
            if (!"Nenhuma".equals(selectedRetorno)) {
                int retornoId = Integer.parseInt(selectedRetorno.split(" \\| ")[0]);
                retorno = consultaController.buscar(retornoId);
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

            if (atualizando == null) { // Criando nova consulta
                consultaController.salvar(
                        paciente,
                        edtDescricao.getText(),
                        formattedData,
                        edtDuracao.getText(),
                        edtValor.getText(),
                        StatusProcedimentos.AGENDADA.getValor(),
                        null,
                        null,
                        medico,
                        retorno);
                dispose();
            } else { // Editando consulta
                consultaController.editar(
                        atualizando.getId(),
                        paciente,
                        edtDescricao.getText(),
                        formattedData,
                        edtDuracao.getText(),
                        edtValor.getText(),
                        (String) cbStatus.getSelectedItem(),
                        null,
                        null,
                        medico,
                        retorno);
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarOpcoesCbRetorno() {
        String pacienteStr = (String) cbPaciente.getSelectedItem();
        String medicoStr = (String) cbMedico.getSelectedItem();

        if (pacienteStr != null && medicoStr != null) {
            int pacienteId = Integer.parseInt(pacienteStr.split(" \\| ")[0]);
            int medicoId = Integer.parseInt(medicoStr.split(" \\| ")[0]);

            String[] options = getConsultasOptions(pacienteId, medicoId);
            cbRetorno.setModel(new DefaultComboBoxModel<>(options));
        }
    }

    private void cbPacienteActionPerformed(ActionEvent evt) {
        atualizarOpcoesCbRetorno();
    }

    private void cbMedicoActionPerformed(ActionEvent evt) {
        atualizarOpcoesCbRetorno();
    }
}
