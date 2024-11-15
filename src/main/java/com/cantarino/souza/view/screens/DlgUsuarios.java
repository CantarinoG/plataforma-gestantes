package com.cantarino.souza.view.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cantarino.souza.controller.AdminController;
import com.cantarino.souza.controller.GestanteController;
import com.cantarino.souza.controller.MedicoController;
import com.cantarino.souza.controller.SecretarioController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.view.components.*;

public class DlgUsuarios extends JDialog {

    JPanel panBackground;
    JLabel lblAction;
    JPanel panCategories;
    JButton btnGestantes;
    JButton btnMedicos;
    JButton btnSecretarios;
    JButton btnAdmin;
    JPanel panTable;
    JPanel panOptions;
    JButton btnCadastrarUsuario;
    JButton btnEditarUsuario;
    JButton btnDeletarUsuario;
    JTable grdUsuarios;
    JScrollPane scrollPane;

    GestanteController gestanteController;
    MedicoController medicoController;
    SecretarioController secretarioController;
    AdminController adminController;

    private final int GERENCIANDO_GESTANTE = 0;
    private final int GERENCIANDO_MEDICO = 1;
    private final int GERENCIANDO_SECRETARIO = 2;
    private final int GERENCIANDO_ADM = 3;

    private int gerenciando = GERENCIANDO_GESTANTE;

    public DlgUsuarios(JFrame parent, boolean modal) {
        super(parent, modal);
        gestanteController = new GestanteController();
        medicoController = new MedicoController();
        secretarioController = new SecretarioController();
        adminController = new AdminController();
        initComponents();
        gestanteController.atualizarTabela(grdUsuarios);
    }

    private void initComponents() {
        setTitle("Gestão de Pacientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new GridBagLayout());
        panBackground.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(panBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        lblAction = new JLabel("Gestão de Pacientes");
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panBackground.add(lblAction, gbc);

        panCategories = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panCategories.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panBackground.add(panCategories, gbc);

        btnGestantes = new RoundedButton("Paciente", 50);
        btnGestantes.setPreferredSize(new Dimension(200, 55));
        btnGestantes.setMinimumSize(new Dimension(200, 55));
        btnGestantes.setMaximumSize(new Dimension(200, 55));
        btnGestantes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGestantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestantesActionPerformed(evt);
            }
        });
        panCategories.add(btnGestantes);

        btnMedicos = new RoundedButton("Médico(a)", 50);
        btnMedicos.setPreferredSize(new Dimension(200, 55));
        btnMedicos.setMinimumSize(new Dimension(200, 55));
        btnMedicos.setMaximumSize(new Dimension(200, 55));
        btnMedicos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicosActionPerformed(evt);
            }
        });
        panCategories.add(btnMedicos);

        btnSecretarios = new RoundedButton("Secretário(a)", 50);
        btnSecretarios.setPreferredSize(new Dimension(200, 55));
        btnSecretarios.setMinimumSize(new Dimension(200, 55));
        btnSecretarios.setMaximumSize(new Dimension(200, 55));
        btnSecretarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSecretarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecretariosActionPerformed(evt);
            }
        });
        panCategories.add(btnSecretarios);

        btnAdmin = new RoundedButton("Administrador(a)", 50);
        btnAdmin.setPreferredSize(new Dimension(200, 55));
        btnAdmin.setMinimumSize(new Dimension(200, 55));
        btnAdmin.setMaximumSize(new Dimension(200, 55));
        btnAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        panCategories.add(btnAdmin);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        grdUsuarios = new JTable();
        grdUsuarios.setModel(new DefaultTableModel(
                new Object[][] {
                        {},
                        {},
                        {},
                        {}
                },
                new String[] {
                }));
        grdUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdUsuariosMouseClicked(evt);
            }
        });
        grdUsuarios.setFillsViewportHeight(true);
        grdUsuarios.setBackground(AppColors.FIELD_PINK);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(grdUsuarios);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panBackground.add(scrollPane, gbc);

        panOptions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panOptions.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panBackground.add(panOptions, gbc);

        btnCadastrarUsuario = new RoundedButton("Cadastrar Usuário", 50);
        btnCadastrarUsuario.setPreferredSize(new Dimension(200, 55));
        btnCadastrarUsuario.setMinimumSize(new Dimension(200, 55));
        btnCadastrarUsuario.setMaximumSize(new Dimension(200, 55));
        btnCadastrarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarUsuarioActionPerformed(evt);
            }
        });
        panOptions.add(btnCadastrarUsuario);

        btnEditarUsuario = new RoundedButton("Editar Usuário", 50);
        btnEditarUsuario.setPreferredSize(new Dimension(200, 55));
        btnEditarUsuario.setMinimumSize(new Dimension(200, 55));
        btnEditarUsuario.setMaximumSize(new Dimension(200, 55));
        btnEditarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });
        panOptions.add(btnEditarUsuario);

        btnDeletarUsuario = new RoundedButton("Deletar Usuário", 50);
        btnDeletarUsuario.setPreferredSize(new Dimension(200, 55));
        btnDeletarUsuario.setMinimumSize(new Dimension(200, 55));
        btnDeletarUsuario.setMaximumSize(new Dimension(200, 55));
        btnDeletarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeletarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarUsuarioActionPerformed(evt);
            }
        });
        panOptions.add(btnDeletarUsuario);
    }

    private void btnGestantesActionPerformed(java.awt.event.ActionEvent evt) {
        lblAction.setText("Gestão de Pacientes");
        btnCadastrarUsuario.setText("Cadastrar Usuário");
        btnEditarUsuario.setText("Editar Usuário");
        btnDeletarUsuario.setText("Deletar Usuário");
        gestanteController.atualizarTabela(grdUsuarios);
        panBackground.repaint();
        gerenciando = GERENCIANDO_GESTANTE;
    }

    private void btnMedicosActionPerformed(java.awt.event.ActionEvent evt) {
        lblAction.setText("Gestão de Médicos");
        btnCadastrarUsuario.setText("Cadastrar Médico(a)");
        btnEditarUsuario.setText("Editar Médico(a)");
        btnDeletarUsuario.setText("Deletar Médico(a)");
        medicoController.atualizarTabela(grdUsuarios);
        panBackground.repaint();
        gerenciando = GERENCIANDO_MEDICO;
    }

    private void btnSecretariosActionPerformed(java.awt.event.ActionEvent evt) {
        lblAction.setText("Gestão de Secretários");
        btnCadastrarUsuario.setText("Cadastrar Secretário(a)");
        btnEditarUsuario.setText("Editar Secretário(a)");
        btnDeletarUsuario.setText("Deletar Secretário(a)");
        secretarioController.atualizarTabela(grdUsuarios);
        panBackground.repaint();
        gerenciando = GERENCIANDO_SECRETARIO;
    }

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {
        lblAction.setText("Gestão de Administradores");
        btnCadastrarUsuario.setText("Cadastrar Administador(a)");
        btnEditarUsuario.setText("Editar Administrador(a)");
        btnDeletarUsuario.setText("Deletar Administrador(a)");
        adminController.atualizarTabela(grdUsuarios);
        panBackground.repaint();
        gerenciando = GERENCIANDO_ADM;
    }

    private void btnCadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        switch (gerenciando) {
            case GERENCIANDO_GESTANTE:
                DlgCadastroGestantes dlgGestante = new DlgCadastroGestantes(this, true);
                dlgGestante.setVisible(true);
                gestanteController.atualizarTabela(grdUsuarios);
                break;
            case GERENCIANDO_MEDICO:
                DlgCadastroMedicos dlgMedico = new DlgCadastroMedicos(this, true);
                dlgMedico.setVisible(true);
                medicoController.atualizarTabela(grdUsuarios);
                break;
            case GERENCIANDO_SECRETARIO:
                DlgCadastroSecretarios dlgSecretario = new DlgCadastroSecretarios(this, true);
                dlgSecretario.setVisible(true);
                secretarioController.atualizarTabela(grdUsuarios);
                break;
            case GERENCIANDO_ADM:
                DlgCadastroAdm dlgAdm = new DlgCadastroAdm(this, true);
                dlgAdm.setVisible(true);
                adminController.atualizarTabela(grdUsuarios);
                break;
            default:
        }
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdUsuarios.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdUsuarios.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;
        Object selectedObject = getObjetoSelecionadoNaGrid();
        if (selectedObject == null) {
            JOptionPane.showMessageDialog(this, "Seleciona um campo da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        switch (gerenciando) {
            case GERENCIANDO_GESTANTE:
                Gestante gestante = (Gestante) selectedObject;
                id = gestante.getId();
                DlgCadastroGestantes dlgGestante = new DlgCadastroGestantes(this, true, id);
                dlgGestante.setVisible(true);
                gestanteController.atualizarTabela(grdUsuarios);
                break;
            case GERENCIANDO_MEDICO:
                Medico medico = (Medico) selectedObject;
                id = medico.getId();
                break;
            case GERENCIANDO_SECRETARIO:
                Secretario secretario = (Secretario) selectedObject;
                id = secretario.getId();
                break;
            case GERENCIANDO_ADM:
                Admin admin = (Admin) selectedObject;
                id = admin.getId();
                break;
            default:
        }
    }

    private void btnDeletarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de deletar usuario");
    }

    private void grdUsuariosMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {

        }
    }

}
