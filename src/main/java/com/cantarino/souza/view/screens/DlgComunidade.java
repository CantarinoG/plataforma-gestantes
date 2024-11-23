package com.cantarino.souza.view.screens;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;

import com.cantarino.souza.controller.PublicacaoController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.AuthTemp;
import com.cantarino.souza.view.components.*;

public class DlgComunidade extends JDialog {

    JPanel panBackground;
    JPanel panLeft;
    JLabel lblAction;
    JPanel panOptions;
    JButton btnTodos;
    JButton btnEspecialista;
    JButton btnMeus;
    JScrollPane scrArea;
    JScrollBar scrBar;
    JPanel panPublish;
    JTextField edtTitle;
    JTextArea edtBody;
    JButton btnPublish;
    JPanel panTitle;
    JPanel panBody;
    JScrollPane scrBody;
    JCheckBox chkAnonimo;
    JPanel panPosts;

    private PublicacaoController controller;

    private Usuario usuario;

    public DlgComunidade(JFrame parent, boolean modal) {
        super(parent, modal);
        controller = new PublicacaoController();
        usuario = AuthTemp.getInstance().getUsuario();
        initComponents();
        initPosts(false, false);
    }

    private void initComponents() {
        setTitle("Comunidade");
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
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panLeft = new JPanel();
        panLeft.setBackground(AppColors.TRANSPARENT);
        panLeft.setPreferredSize(new Dimension(0, 0));
        panLeft.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panBackground.add(panLeft, gbc);

        panLeft.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 10, 0);
        lblAction = new JLabel("Comunidade", SwingConstants.CENTER);
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        panLeft.add(lblAction, gbc);

        gbc.gridy = 1;
        panOptions = new JPanel(new GridLayout(1, 3, 10, 0));
        panOptions.setBackground(AppColors.TRANSPARENT);
        panOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panOptions.setPreferredSize(new Dimension(0, 75));
        panLeft.add(panOptions, gbc);

        btnTodos = new RoundedButton("Todas as Publicações", 10);
        btnTodos.setPreferredSize(new Dimension(150, 50));
        btnTodos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTodos.setForeground(Color.WHITE);
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });
        panOptions.add(btnTodos);

        btnEspecialista = new RoundedButton("Publicações de Especialistas", 10);
        btnEspecialista.setPreferredSize(new Dimension(150, 50));
        btnEspecialista.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEspecialista.setForeground(Color.WHITE);
        btnEspecialista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEspecialistaActionPerformed(evt);
            }
        });
        panOptions.add(btnEspecialista);

        btnMeus = new RoundedButton("Minhas Publicações", 10);
        btnMeus.setPreferredSize(new Dimension(150, 50));
        btnMeus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMeus.setForeground(Color.WHITE);
        btnMeus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMeusActionPerformed(evt);
            }
        });
        panOptions.add(btnMeus);

        gbc.gridy = 2;
        gbc.weighty = 0;
        panPublish = new JPanel();
        panPublish.setLayout(new BoxLayout(panPublish, BoxLayout.Y_AXIS));
        panPublish.setBackground(AppColors.TRANSPARENT);
        panPublish.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK),
                        "Nova Publicação",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new Font("Arial", Font.BOLD, 16)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JPanel fieldsPanel = new JPanel(new FlowLayout());
        fieldsPanel.setBackground(AppColors.TRANSPARENT);

        edtTitle = new JTextField();
        panTitle = createCustomTextfield("Título", edtTitle);
        fieldsPanel.add(panTitle);

        edtBody = new JTextArea();
        edtBody.setRows(3);
        edtBody.setLineWrap(true);
        edtBody.setWrapStyleWord(true);
        edtBody.setBackground(AppColors.FIELD_PINK);
        scrBody = new JScrollPane(edtBody);
        scrBody.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panBody = createCustomTextfield("Conteúdo", scrBody);
        fieldsPanel.add(panBody);

        chkAnonimo = new JCheckBox("Anônimo");
        chkAnonimo.setFont(new Font("Arial", Font.PLAIN, 14));
        chkAnonimo.setBackground(AppColors.TRANSPARENT);
        fieldsPanel.add(chkAnonimo);

        btnPublish = new RoundedButton("Publicar", 10);
        btnPublish.setPreferredSize(new Dimension(150, 50));
        btnPublish.setBackground(AppColors.BUTTON_PINK);
        btnPublish.setForeground(Color.WHITE);
        btnPublish.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPublish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublishActionPerformed(evt);
            }
        });
        fieldsPanel.add(btnPublish);

        panPublish.add(fieldsPanel);
        panLeft.add(panPublish, gbc);

        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        scrArea = new JScrollPane();
        scrArea.setBorder(null);
        panLeft.add(scrArea, gbc);

        scrBar = scrArea.getVerticalScrollBar();
        scrBar.setBackground(AppColors.BUTTON_PINK);
        scrBar.setForeground(AppColors.BUTTON_PINK);

    }

    private void initPosts(boolean showOnlySpecialists, boolean showOnlyMine) {
        List<Publicacao> posts = controller.buscarTodos();

        if (showOnlySpecialists) {
            posts.removeIf(post -> !(post.getAutor() instanceof Medico));
        } else if (showOnlyMine) {
            posts.removeIf(post -> (post.getAutor().getId() != usuario.getId()));
        }

        scrArea.setViewportView(null);

        panPosts = new JPanel();
        panPosts.setLayout(new BoxLayout(panPosts, BoxLayout.Y_AXIS));
        panPosts.setBackground(AppColors.BG);

        for (Publicacao post : posts) {
            JPanel postComponent = createPostComponent(post);
            panPosts.add(postComponent);
        }

        scrArea.setViewportView(panPosts);
        SwingUtilities.invokeLater(() -> scrArea.getViewport().setViewPosition(new Point(0, 0)));
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
        }

        textField.setBackground(AppColors.FIELD_PINK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }

    private JPanel createPostComponent(Publicacao post) {

        JPanel panPost = new JPanel();
        panPost.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panPost.setLayout(new BorderLayout());
        panPost.setBackground(AppColors.TRANSPARENT);

        JPanel panHeader = new JPanel();
        panHeader.setLayout(new BorderLayout());
        panHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panHeader.setBackground(AppColors.BG);

        JLabel lblUserPic = new JLabel();

        if (post.isAnonimo() && usuario instanceof Gestante) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/anon.png")));
        } else if (post.getAutor() instanceof Medico) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/doctor.png")));
        } else if (post.getAutor() instanceof Secretario) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/secretary.png")));
        } else if (post.getAutor() instanceof Admin) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/adm.png")));
        } else if (post.getAutor() instanceof Gestante) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/patient.png")));
        }

        ImageIcon icon = (ImageIcon) lblUserPic.getIcon();
        if (icon != null) {
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            lblUserPic.setIcon(new ImageIcon(scaledImg));
        }
        lblUserPic.setPreferredSize(new Dimension(50, 50));
        panHeader.add(lblUserPic, BorderLayout.WEST);

        JPanel panInfo = new JPanel();
        panInfo.setLayout(new GridLayout(4, 1));
        panInfo.setBackground(AppColors.BG);
        panInfo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel lblTitle = new JLabel(post.getTitulo());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblAuthor = new JLabel(post.getAutor().getNome());
        if (post.isAnonimo() && usuario instanceof Gestante) {
            lblAuthor.setText("Usuário Desconhecido");
        }
        lblAuthor.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTime = new JLabel(post.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        lblTime.setFont(new Font("Arial", Font.ITALIC, 12));

        JLabel lblSpecialist = new JLabel("");
        lblSpecialist.setFont(new Font("Arial", Font.BOLD, 12));
        lblSpecialist.setForeground(AppColors.BUTTON_PINK);

        if (post.getAutor() instanceof Medico) {
            lblSpecialist.setText("Dica de Especialista");
        } else if (post.getAutor() instanceof Secretario || post.getAutor() instanceof Admin) {
            lblSpecialist.setText("Publicação da Moderação");
        }

        panInfo.add(lblAuthor);
        panInfo.add(lblTitle);
        panInfo.add(lblTime);
        panInfo.add(lblSpecialist);

        panHeader.add(panInfo, BorderLayout.CENTER);

        JTextArea txtAreaBody = new JTextArea();
        txtAreaBody.setEditable(false);
        txtAreaBody.setWrapStyleWord(true);
        txtAreaBody.setLineWrap(true);
        txtAreaBody.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtAreaBody.setBackground(AppColors.BG);
        txtAreaBody.setText(post.getCorpo());

        JPanel panButtom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButtom.setBackground(AppColors.BG);

        JButton btnComments = new RoundedButton("Ver Comentários", 10);
        btnComments.setBackground(AppColors.BUTTON_PINK);
        btnComments.setForeground(Color.WHITE);
        btnComments.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommentsActionsPerformed(evt, post.getId());
            }
        });
        panButtom.add(btnComments);

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
            panButtom.add(btnDelete);
        }

        panPost.add(panHeader, BorderLayout.NORTH);
        panPost.add(txtAreaBody, BorderLayout.CENTER);
        panPost.add(panButtom, BorderLayout.SOUTH);

        return panPost;
    }

    private void btnPublishActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            controller.cadastrar(edtTitle.getText(), edtBody.getText(), chkAnonimo.isSelected(), LocalDateTime.now(),
                    usuario, null);
            edtTitle.setText("");
            edtBody.setText("");
            chkAnonimo.setSelected(false);
            JOptionPane.showMessageDialog(this, "Publicação realizada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            initPosts(false, false);
            this.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {
        initPosts(false, false);
        this.repaint();
    }

    private void btnEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {
        initPosts(true, false);
        this.repaint();
    }

    private void btnMeusActionPerformed(java.awt.event.ActionEvent evt) {
        initPosts(false, true);
        this.repaint();
    }

    private void btnCommentsActionsPerformed(java.awt.event.ActionEvent evt, int id) {
        DlgComentarios dialog = new DlgComentarios(this, true, id);
        dialog.setVisible(true);
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt, int id) {
        int option = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir esta publicação?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            controller.excluir(id);
            initPosts(false, false);
            this.repaint();
        }
    }

}
