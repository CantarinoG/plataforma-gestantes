package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.SecretarioController;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.BackgroundPanel;
import com.cantarino.souza.view.components.RoundedButton;

import java.awt.*;

public class DlgSecretarios extends JDialog {

    private JPanel panFundo;
    private JPanel panHeader;
    private JLabel lblTitulo;
    private JPanel panConteudo;
    private JTable grdSecretarios;
    private JScrollPane scrollPane;
    private JPanel panFooter;
    private JButton btnCadastrar;
    private JButton btnEditar;
    private JButton btnDeletar;

    private SecretarioController secretarioController;
    private AutenticacaoController autenticacaoController;

    private Usuario usuario = null;

    public DlgSecretarios(JFrame parent, boolean modal) {
        super(parent, modal);

        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();
        secretarioController = new SecretarioController();

        initComponents();

        secretarioController.atualizarTabela(grdSecretarios);
    }

    private void initComponents() {
        setTitle("Secretários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panFundo = new BackgroundPanel("/images/background.png");
        panFundo.setLayout(new BorderLayout());
        setContentPane(panFundo);

        panHeader = new JPanel();
        panHeader.setPreferredSize(new Dimension(getWidth(), 80));
        panHeader.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panHeader.setBackground(AppColors.BUTTON_PINK);
        panHeader.setOpaque(true);
        panHeader.setLayout(new GridBagLayout());

        lblTitulo = new JLabel("Secretários");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        panHeader.add(lblTitulo, gbc);

        panFundo.add(panHeader, BorderLayout.NORTH);
        panConteudo = new PanConsultasAgendadas();
        panConteudo.setLayout(new BorderLayout());
        panConteudo.setBackground(new Color(255, 255, 255));
        panConteudo.setOpaque(true);

        grdSecretarios = new JTable();
        grdSecretarios.setModel(new DefaultTableModel(
                new Object[][] {
                        {},
                        {},
                        {},
                        {}
                },
                new String[] {
                }));
        scrollPane = new JScrollPane(grdSecretarios);
        panConteudo.add(scrollPane, BorderLayout.CENTER);

        panFooter = new JPanel();
        panFooter.setPreferredSize(new Dimension(getWidth(), 80));
        panFooter.setBackground(AppColors.BUTTON_PINK);
        panFooter.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

        btnCadastrar = new RoundedButton("Cadastrar", 10);
        btnCadastrar.setPreferredSize(new Dimension(150, 50));
        btnCadastrar.setBackground(Color.WHITE);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnEditar = new RoundedButton("Editar", 10);
        btnEditar.setPreferredSize(new Dimension(150, 50));
        btnEditar.setBackground(Color.WHITE);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar = new RoundedButton("Deletar", 10);
        btnDeletar.setPreferredSize(new Dimension(150, 50));
        btnDeletar.setBackground(Color.WHITE);
        btnDeletar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        panFooter.add(btnCadastrar);
        panFooter.add(btnEditar);
        panFooter.add(btnDeletar);

        panConteudo.add(panFooter, BorderLayout.SOUTH);

        panFundo.add(panConteudo, BorderLayout.CENTER);
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        DlgCadastroSecretarios dialog = new DlgCadastroSecretarios(this, true);
        dialog.setVisible(true);
        secretarioController.atualizarTabela(grdSecretarios);
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdSecretarios.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdSecretarios.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Secretario secretario = (Secretario) selectedObject;

        DlgCadastroSecretarios dialog = new DlgCadastroSecretarios(this, true, secretario.getId());
        dialog.setVisible(true);
        secretarioController.atualizarTabela(grdSecretarios);

    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {

        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Secretario secretario = (Secretario) selectedObject;

        if (secretario.getId() == usuario.getId()) {
            JOptionPane.showMessageDialog(this, "Você não pode deletar a si mesmo", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = { "Sim", "Não" };
        int option = JOptionPane.showOptionDialog(this,
                "Tem certeza que deseja excluir este secretário?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (option == JOptionPane.YES_OPTION) {
            secretarioController.deletar(secretario.getId());
            secretarioController.atualizarTabela(grdSecretarios);
        }
    }

}
