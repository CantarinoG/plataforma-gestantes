package com.cantarino.souza.view.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.SecretarioController;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.BackgroundPanel;
import com.cantarino.souza.view.components.RoundedButton;

import java.awt.*;

public class DlgSecretarios extends JDialog {

    private JPanel panBackground;
    private JPanel panHeader;
    private JLabel lblTitle;
    private JPanel panContent;
    private JTable grdSecretarios;
    private JScrollPane scrollPane;
    private JPanel panFooter;
    private JButton btnCadastrar;
    private JButton btnEditar;
    private JButton btnDeletar;

    private SecretarioController secretarioController;

    public DlgSecretarios(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        secretarioController = new SecretarioController();
        secretarioController.atualizarTabela(grdSecretarios);
    }

    private void initComponents() {
        setTitle("Secretários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new BorderLayout());
        setContentPane(panBackground);

        panHeader = new JPanel();
        panHeader.setPreferredSize(new Dimension(getWidth(), 80));
        panHeader.setBorder(BorderFactory.createEmptyBorder(5, 64, 10, 5));
        panHeader.setBackground(AppColors.BUTTON_PINK);
        panHeader.setOpaque(true);
        panHeader.setLayout(new GridBagLayout());

        lblTitle = new JLabel("Secretários");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(AppColors.TITLE_BLUE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        panHeader.add(lblTitle, gbc);

        panBackground.add(panHeader, BorderLayout.NORTH);
        panContent = new PanConsultasAgendadas();
        panContent.setLayout(new BorderLayout());
        panContent.setBackground(new Color(255, 255, 255));
        panContent.setOpaque(true);

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
        panContent.add(scrollPane, BorderLayout.CENTER);

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

        panContent.add(panFooter, BorderLayout.SOUTH);

        panBackground.add(panContent, BorderLayout.CENTER);
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
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Secretario secretario = (Secretario) selectedObject;
        id = secretario.getId();
        DlgCadastroSecretarios dialog = new DlgCadastroSecretarios(this, true, id);
        dialog.setVisible(true);
        secretarioController.atualizarTabela(grdSecretarios);

    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Secretario secretario = (Secretario) selectedObject;
        id = secretario.getId();

        int option = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir este secretário?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            secretarioController.excluir(id);
            secretarioController.atualizarTabela(grdSecretarios);
        }
    }

}
