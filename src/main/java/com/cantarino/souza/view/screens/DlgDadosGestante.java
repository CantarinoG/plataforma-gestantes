package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.cantarino.souza.controller.ConsultaController;
import com.cantarino.souza.controller.ExameController;
import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.controller.PublicacaoController;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.RoundedButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDadosGestante extends JDialog {

    JPanel panFundo;
    JPanel panColuna;
    JLabel lblTitulo;
    JLabel lblNome;
    JLabel lblCPF;
    JPanel panNome;
    JPanel panBotao;
    JButton btnCriarConta;
    JPanel panData;
    JLabel lblEmail;
    JLabel lblDataNascimento;
    JLabel lblTelefone;
    JLabel lblEndereco;
    JLabel lblPrevisaoParto;
    JLabel lblContatoEmergencia;
    JLabel lblTipoSanguineo;
    JLabel lblHistoricoMedico;
    JPanel panCpf;
    JPanel panEmail;
    JPanel panTelefone;
    JPanel panEndereco;
    JPanel panPrevisaoParto;
    JPanel panContatoEmergencia;
    JPanel panTipoSanguineo;
    JPanel panHistoricoMedico;
    JButton btnConsultas;
    JButton btnExames;
    JButton btnPublicacoes;
    JTable grdDados;
    JScrollPane scrollPane;

    private GestanteController gestanteController;
    private ConsultaController consultaController;
    private ExameController exameController;
    private PublicacaoController publicacaoController;

    private final int CONSULTA = 0;
    private final int EXAME = 1;
    private final int PUBLICACAO = 2;

    private int exibindo;

    private Gestante gestante;

    public DlgDadosGestante(JDialog parent, boolean modal, int id) {
        super(parent, modal);

        gestanteController = new GestanteController();
        consultaController = new ConsultaController();
        exameController = new ExameController();
        publicacaoController = new PublicacaoController();
        gestante = gestanteController.buscar(id);
        exibindo = CONSULTA;

        initComponents();

        consultaController.atualizarTabelaPorGestante(grdDados, gestante.getId());
    }

    private void initComponents() {
        setTitle("Dados da Gestante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
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

        lblTitulo = new JLabel("Dados da Gestante");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panFundo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        panColuna = new JPanel();
        panColuna.setLayout(new GridLayout(6, 2, 20, 10));
        panColuna.setBackground(AppColors.TRANSPARENT);
        panFundo.add(panColuna, gbc);

        lblCPF = new JLabel(gestante.getCpf());
        lblCPF.setFont(new Font("Arial", Font.PLAIN, 22));
        lblCPF.setBackground(AppColors.FIELD_PINK);
        lblCPF.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panCpf = criarCampoCustomizado("CPF", lblCPF);
        panColuna.add(panCpf);

        lblNome = new JLabel(gestante.getNome());
        lblNome.setFont(new Font("Arial", Font.PLAIN, 22));
        lblNome.setBackground(AppColors.FIELD_PINK);
        lblNome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panNome = criarCampoCustomizado("Nome", lblNome);
        panColuna.add(panNome);

        lblEmail = new JLabel(gestante.getEmail());
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEmail.setBackground(AppColors.FIELD_PINK);
        lblEmail.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEmail = criarCampoCustomizado("Email", lblEmail);
        panColuna.add(panEmail);

        lblDataNascimento = new JLabel(
                gestante.getDataNascimento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblDataNascimento.setFont(new Font("Arial", Font.PLAIN, 22));
        lblDataNascimento.setBackground(AppColors.FIELD_PINK);
        lblDataNascimento.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panData = criarCampoCustomizado("Data de Nascimento", lblDataNascimento);
        panColuna.add(panData);

        lblTelefone = new JLabel(gestante.getTelefone());
        lblTelefone.setFont(new Font("Arial", Font.PLAIN, 22));
        lblTelefone.setBackground(AppColors.FIELD_PINK);
        lblTelefone.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panTelefone = criarCampoCustomizado("Telefone", lblTelefone);
        panColuna.add(panTelefone);

        lblEndereco = new JLabel(gestante.getEndereco());
        lblEndereco.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEndereco.setBackground(AppColors.FIELD_PINK);
        lblEndereco.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panEndereco = criarCampoCustomizado("Endereço", lblEndereco);
        panColuna.add(panEndereco);

        lblPrevisaoParto = new JLabel(
                gestante.getPrevisaoParto().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblPrevisaoParto.setFont(new Font("Arial", Font.PLAIN, 22));
        lblPrevisaoParto.setBackground(AppColors.FIELD_PINK);
        lblPrevisaoParto.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panPrevisaoParto = criarCampoCustomizado("Previsão de Parto", lblPrevisaoParto);
        panColuna.add(panPrevisaoParto);

        lblContatoEmergencia = new JLabel(gestante.getContatoEmergencia());
        lblContatoEmergencia.setFont(new Font("Arial", Font.PLAIN, 22));
        lblContatoEmergencia.setBackground(AppColors.FIELD_PINK);
        lblContatoEmergencia.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panContatoEmergencia = criarCampoCustomizado("Contato de Emergência", lblContatoEmergencia);
        panColuna.add(panContatoEmergencia);

        lblTipoSanguineo = new JLabel(gestante.getTipoSanguineo());
        lblTipoSanguineo.setFont(new Font("Arial", Font.PLAIN, 22));
        lblTipoSanguineo.setBackground(AppColors.FIELD_PINK);
        lblTipoSanguineo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panTipoSanguineo = criarCampoCustomizado("Tipo Sanguíneo", lblTipoSanguineo);
        panColuna.add(panTipoSanguineo);

        lblHistoricoMedico = new JLabel(gestante.getHistoricoMedico());
        lblHistoricoMedico.setFont(new Font("Arial", Font.PLAIN, 22));
        lblHistoricoMedico.setBackground(AppColors.FIELD_PINK);
        lblHistoricoMedico.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panHistoricoMedico = criarCampoCustomizado("Histórico Médico(Opcional)", lblHistoricoMedico);
        panColuna.add(panHistoricoMedico);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        grdDados = new JTable();
        grdDados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdDadosActionPerformed(evt);
            }
        });
        scrollPane = new JScrollPane(grdDados);
        panFundo.add(scrollPane, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);

        panBotao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panBotao.setBackground(AppColors.TRANSPARENT);

        btnConsultas = new RoundedButton("Consultas", 10);
        btnConsultas.setPreferredSize(new Dimension(150, 50));
        btnConsultas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConsultas.setBackground(Color.WHITE);
        btnConsultas.addActionListener(evt -> btnConsultasActionPerformed(evt));

        btnExames = new RoundedButton("Exames", 10);
        btnExames.setPreferredSize(new Dimension(150, 50));
        btnExames.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExames.setBackground(Color.WHITE);
        btnExames.addActionListener(evt -> btnExamesActionPerformed(evt));

        btnPublicacoes = new RoundedButton("Publicações", 10);
        btnPublicacoes.setPreferredSize(new Dimension(150, 50));
        btnPublicacoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPublicacoes.setBackground(Color.WHITE);
        btnPublicacoes.addActionListener(evt -> btnPublicacoesActionPerformed(evt));

        panBotao.add(btnConsultas);
        panBotao.add(btnExames);
        panBotao.add(btnPublicacoes);

        panFundo.add(panBotao, gbc);
    }

    private JPanel criarCampoCustomizado(String hint, JComponent textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        fieldPanel.setPreferredSize(new Dimension(300, 60));
        fieldPanel.setBackground(AppColors.FIELD_PINK);

        JLabel hintLabel = new JLabel(hint);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldPanel.add(hintLabel, BorderLayout.NORTH);

        String text = "";
        if (textField instanceof JLabel) {
            text = ((JLabel) textField).getText();
        }

        JLabel valueLabel = new JLabel(text);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        valueLabel.setBackground(AppColors.FIELD_PINK);
        valueLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(valueLabel, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void btnConsultasActionPerformed(ActionEvent evt) {
        consultaController.atualizarTabelaPorGestante(grdDados, gestante.getId());
        exibindo = CONSULTA;
    }

    private void btnExamesActionPerformed(ActionEvent evt) {
        exameController.atualizarTabelaPorGestante(grdDados, gestante.getId());
        exibindo = EXAME;
    }

    private void btnPublicacoesActionPerformed(ActionEvent evt) {
        publicacaoController.atualizarTabelaPorGestante(grdDados, gestante.getId());
        grdDados.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
        exibindo = PUBLICACAO;
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdDados.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdDados.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void verRelatorioConsulta() {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            return;
        }

        Consulta consulta = (Consulta) selectedObject;
        if (consulta.getRelatorio() == null) {
            JOptionPane.showMessageDialog(this, "Essa consulta não possui nenhum relatório cadastrado", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            DlgDadosRelatorio dialog = new DlgDadosRelatorio(this, true, consulta);
            dialog.setVisible(true);
        }
    }

    private void verRelatorioExame() {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            return;
        }

        Exame exame = (Exame) selectedObject;
        if (exame.getRelatorio() == null) {
            JOptionPane.showMessageDialog(this, "Esse exame não possui nenhum relatório cadastrado", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            DlgDadosRelatorio dialog = new DlgDadosRelatorio(this, true, exame);
            dialog.setVisible(true);
        }
    }

    private void grdDadosActionPerformed(MouseEvent evt) {
        int row = -1;

        if (evt.getClickCount() == 2) {
            row = grdDados.getSelectedRow();
            if (row != -1) {
                switch (exibindo) {
                    case CONSULTA:
                        verRelatorioConsulta();
                        break;
                    case EXAME:
                        verRelatorioExame();
                        break;
                    case PUBLICACAO:
                        break;
                }
            }
        }
    }
}
