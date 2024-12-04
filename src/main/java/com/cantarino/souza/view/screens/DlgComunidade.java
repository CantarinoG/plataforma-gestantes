package com.cantarino.souza.view.screens;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.PublicacaoController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.components.*;

public class DlgComunidade extends JDialog {

    JPanel panFundo;
    JPanel panEsquerdo;
    JLabel lblTitulo;
    JPanel panOpcoes;
    JButton btnTodos;
    JButton btnEspecialista;
    JButton btnMeus;
    JScrollPane scrArea;
    JScrollBar scrBar;
    JPanel panPublicar;
    JTextField edtTitulo;
    JTextArea edtCorpo;
    JButton btnPublicar;
    JPanel panTitulo;
    JPanel panCorpo;
    JScrollPane scrCorpo;
    JCheckBox chkAnonimo;
    JPanel panPosts;

    private PublicacaoController controller;
    private AutenticacaoController autenticacaoController;

    private Usuario usuario = null;

    public DlgComunidade(JFrame parent, boolean modal) {
        super(parent, modal);

        controller = new PublicacaoController();
        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuario();

        initComponents();
        initPublicacoes(false, false);
    }

    private void initComponents() {
        setTitle("Comunidade");
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
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panEsquerdo = new JPanel();
        panEsquerdo.setBackground(AppColors.TRANSPARENT);
        panEsquerdo.setPreferredSize(new Dimension(0, 0));
        panEsquerdo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panFundo.add(panEsquerdo, gbc);

        panEsquerdo.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 10, 0);
        lblTitulo = new JLabel("Comunidade", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 64));
        lblTitulo.setForeground(AppColors.TITLE_BLUE);
        panEsquerdo.add(lblTitulo, gbc);

        gbc.gridy = 1;
        panOpcoes = new JPanel(new GridLayout(1, 3, 10, 0));
        panOpcoes.setBackground(AppColors.TRANSPARENT);
        panOpcoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panOpcoes.setPreferredSize(new Dimension(0, 75));
        panEsquerdo.add(panOpcoes, gbc);

        btnTodos = new RoundedButton("Todas as Publicações", 10);
        btnTodos.setPreferredSize(new Dimension(150, 50));
        btnTodos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTodos.setForeground(Color.WHITE);
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });
        panOpcoes.add(btnTodos);

        btnEspecialista = new RoundedButton("Publicações de Especialistas", 10);
        btnEspecialista.setPreferredSize(new Dimension(150, 50));
        btnEspecialista.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEspecialista.setForeground(Color.WHITE);
        btnEspecialista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEspecialistaActionPerformed(evt);
            }
        });
        panOpcoes.add(btnEspecialista);

        btnMeus = new RoundedButton("Minhas Publicações", 10);
        btnMeus.setPreferredSize(new Dimension(150, 50));
        btnMeus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMeus.setForeground(Color.WHITE);
        btnMeus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMeusActionPerformed(evt);
            }
        });
        panOpcoes.add(btnMeus);

        gbc.gridy = 2;
        gbc.weighty = 0;
        panPublicar = new JPanel();
        panPublicar.setLayout(new BoxLayout(panPublicar, BoxLayout.Y_AXIS));
        panPublicar.setBackground(AppColors.TRANSPARENT);
        panPublicar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK),
                        "Nova Publicação",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new Font("Arial", Font.BOLD, 16)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JPanel fieldsPanel = new JPanel(new FlowLayout());
        fieldsPanel.setBackground(AppColors.TRANSPARENT);

        edtTitulo = new JTextField();
        panTitulo = criarTextFieldCustomizado("Título", edtTitulo);
        fieldsPanel.add(panTitulo);

        edtCorpo = new JTextArea();
        edtCorpo.setRows(3);
        edtCorpo.setLineWrap(true);
        edtCorpo.setWrapStyleWord(true);
        edtCorpo.setBackground(AppColors.FIELD_PINK);
        scrCorpo = new JScrollPane(edtCorpo);
        scrCorpo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panCorpo = criarTextFieldCustomizado("Conteúdo", scrCorpo);
        fieldsPanel.add(panCorpo);

        chkAnonimo = new JCheckBox("Anônimo");
        chkAnonimo.setFont(new Font("Arial", Font.PLAIN, 14));
        chkAnonimo.setBackground(AppColors.TRANSPARENT);
        fieldsPanel.add(chkAnonimo);

        btnPublicar = new RoundedButton("Publicar", 10);
        btnPublicar.setPreferredSize(new Dimension(150, 50));
        btnPublicar.setBackground(AppColors.BUTTON_PINK);
        btnPublicar.setForeground(Color.WHITE);
        btnPublicar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicarActionPerformed(evt);
            }
        });
        fieldsPanel.add(btnPublicar);

        panPublicar.add(fieldsPanel);
        panEsquerdo.add(panPublicar, gbc);

        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        scrArea = new JScrollPane();
        scrArea.setBorder(null);
        panEsquerdo.add(scrArea, gbc);

        scrBar = scrArea.getVerticalScrollBar();
        scrBar.setBackground(AppColors.BUTTON_PINK);
        scrBar.setForeground(AppColors.BUTTON_PINK);

    }

    private void initPublicacoes(boolean filtrarEspecialistas, boolean filtrarMeus) {
        List<Publicacao> posts = controller.buscarTodos();

        if (filtrarEspecialistas) {
            posts.removeIf(post -> !(post.getAutor() instanceof Medico));
        } else if (filtrarMeus) {
            posts.removeIf(post -> (post.getAutor().getId() != usuario.getId()));
        }

        scrArea.setViewportView(null);

        panPosts = new JPanel();
        panPosts.setLayout(new BoxLayout(panPosts, BoxLayout.Y_AXIS));
        panPosts.setBackground(AppColors.BG);

        for (Publicacao post : posts) {
            JPanel postComponent = criarComponentePublicacao(post);
            panPosts.add(postComponent);
        }

        scrArea.setViewportView(panPosts);
        SwingUtilities.invokeLater(() -> scrArea.getViewport().setViewPosition(new Point(0, 0)));
    }

    private JPanel criarTextFieldCustomizado(String hint, JComponent textField) {
        JPanel panCampo = new JPanel();
        panCampo.setLayout(new BorderLayout());
        panCampo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        panCampo.setPreferredSize(new Dimension(500, 60));
        panCampo.setBackground(AppColors.FIELD_PINK);

        JLabel lblDica = new JLabel(hint);
        lblDica.setFont(new Font("Arial", Font.BOLD, 14));
        panCampo.add(lblDica, BorderLayout.NORTH);

        if (textField instanceof JTextField) {
            ((JTextField) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        } else if (textField instanceof JTextArea) {
            ((JTextArea) textField).setFont(new Font("Arial", Font.PLAIN, 14));
        }

        textField.setBackground(AppColors.FIELD_PINK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        panCampo.add(textField, BorderLayout.CENTER);

        return panCampo;
    }

    private JPanel criarComponentePublicacao(Publicacao post) {

        JPanel panPost = new JPanel();
        panPost.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panPost.setLayout(new BorderLayout());
        panPost.setBackground(AppColors.TRANSPARENT);

        JPanel panHeader = new JPanel();
        panHeader.setLayout(new BorderLayout());
        panHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panHeader.setBackground(AppColors.BG);

        JLabel lblIconeUsuario = new JLabel();

        if (post.isAnonimo() && usuario instanceof Gestante) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/anon.png")));
        } else if (post.getAutor() instanceof Medico) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/doctor.png")));
        } else if (post.getAutor() instanceof Secretario) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/secretary.png")));
        } else if (post.getAutor() instanceof Admin) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/adm.png")));
        } else if (post.getAutor() instanceof Gestante) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/patient.png")));
        }

        ImageIcon icone = (ImageIcon) lblIconeUsuario.getIcon();
        if (icone != null) {
            Image img = icone.getImage();
            Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            lblIconeUsuario.setIcon(new ImageIcon(scaledImg));
        }
        lblIconeUsuario.setPreferredSize(new Dimension(50, 50));
        panHeader.add(lblIconeUsuario, BorderLayout.WEST);

        JPanel panInfo = new JPanel();
        panInfo.setLayout(new GridLayout(4, 1));
        panInfo.setBackground(AppColors.BG);
        panInfo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel lblTitulo = new JLabel(post.getTitulo());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblAutor = new JLabel(post.getAutor().getNome());
        if (post.isAnonimo() && usuario instanceof Gestante) {
            lblAutor.setText("Usuário Desconhecido");
        }
        lblAutor.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTempo = new JLabel(post.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        lblTempo.setFont(new Font("Arial", Font.ITALIC, 12));

        JLabel lblEspecial = new JLabel("");
        lblEspecial.setFont(new Font("Arial", Font.BOLD, 12));
        lblEspecial.setForeground(AppColors.BUTTON_PINK);

        if (post.getAutor() instanceof Medico) {
            lblEspecial.setText("Dica de Especialista");
        } else if (post.getAutor() instanceof Secretario || post.getAutor() instanceof Admin) {
            lblEspecial.setText("Publicação da Moderação");
        }

        panInfo.add(lblAutor);
        panInfo.add(lblTitulo);
        panInfo.add(lblTempo);
        panInfo.add(lblEspecial);

        panHeader.add(panInfo, BorderLayout.CENTER);

        JTextArea txtAreaCorpo = new JTextArea();
        txtAreaCorpo.setEditable(false);
        txtAreaCorpo.setWrapStyleWord(true);
        txtAreaCorpo.setLineWrap(true);
        txtAreaCorpo.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaCorpo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtAreaCorpo.setBackground(AppColors.BG);
        txtAreaCorpo.setText(post.getCorpo());

        JPanel panBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panBotoes.setBackground(AppColors.BG);

        JButton btnComentarios = new RoundedButton("Ver Comentários", 10);
        btnComentarios.setBackground(AppColors.BUTTON_PINK);
        btnComentarios.setForeground(Color.WHITE);
        btnComentarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComentarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommentsActionsPerformed(evt, post.getId());
            }
        });
        panBotoes.add(btnComentarios);

        if ((post.getAutor().getId() == usuario.getId()) || usuario instanceof Admin || usuario instanceof Secretario) {
            JButton btnDelete = new RoundedButton("Deletar Publicação", 10);
            btnDelete.setBackground(AppColors.BUTTON_PINK);
            btnDelete.setForeground(Color.WHITE);
            btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnDelete.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnDeleteActionPerformed(evt, post.getId());
                }
            });
            panBotoes.add(btnDelete);
        }

        panPost.add(panHeader, BorderLayout.NORTH);
        panPost.add(txtAreaCorpo, BorderLayout.CENTER);
        panPost.add(panBotoes, BorderLayout.SOUTH);

        return panPost;
    }

    private void btnPublicarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            controller.salvar(edtTitulo.getText(), edtCorpo.getText(), chkAnonimo.isSelected(), LocalDateTime.now(),
                    usuario, null);
            edtTitulo.setText("");
            edtCorpo.setText("");
            chkAnonimo.setSelected(false);
            JOptionPane.showMessageDialog(this, "Publicação realizada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            initPublicacoes(false, false);
            this.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {
        initPublicacoes(false, false);
        this.repaint();
    }

    private void btnEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {
        initPublicacoes(true, false);
        this.repaint();
    }

    private void btnMeusActionPerformed(java.awt.event.ActionEvent evt) {
        initPublicacoes(false, true);
        this.repaint();
    }

    private void btnCommentsActionsPerformed(java.awt.event.ActionEvent evt, int id) {
        DlgComentarios dialog = new DlgComentarios(this, true, id);
        dialog.setVisible(true);
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt, int id) {
        Object[] options = { "Sim", "Não" };
        int option = JOptionPane.showOptionDialog(this,
                "Tem certeza que deseja excluir esta publicação?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (option == JOptionPane.YES_OPTION) {
            controller.deletar(id);
            initPublicacoes(false, false);
            this.repaint();
        }
    }

}
