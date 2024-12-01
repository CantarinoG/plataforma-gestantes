package com.cantarino.souza.view.screens;

import javax.swing.*;

import java.util.List;

import com.cantarino.souza.controller.AutenticacaoController;
import com.cantarino.souza.controller.ComentarioController;
import com.cantarino.souza.controller.PublicacaoController;
import com.cantarino.souza.model.entities.Admin;
import com.cantarino.souza.model.entities.Comentario;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Publicacao;
import com.cantarino.souza.model.entities.Secretario;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.view.components.AppColors;
import com.cantarino.souza.view.components.RoundedButton;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DlgComentarios extends JDialog {

    private JScrollPane scrArea;
    private JScrollBar scrBar;
    private JPanel panComments;
    private JPanel panHeader;
    private JTextArea edtComment;
    private JScrollPane scrComment;
    private JPanel panCommentField;
    private JCheckBox chkAnonimo;
    private JButton btnPublish;
    private JPanel panContent;

    private int id;

    private ComentarioController comentarioController;
    private PublicacaoController publicacaoController;
    private AutenticacaoController autenticacaoController;

    private Usuario usuario;

    public DlgComentarios(JDialog parent, boolean modal, int postId) {
        super(parent, modal);
        autenticacaoController = new AutenticacaoController();
        this.id = postId;
        comentarioController = new ComentarioController();
        publicacaoController = new PublicacaoController();
        usuario = autenticacaoController.getUsuario();
        initComponents();
        initComments();
    }

    private void initComponents() {
        setTitle("Comentários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1620, 930);
        setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        setBackground(AppColors.BG);
        setLayout(new BorderLayout());

        panContent = new JPanel(new BorderLayout());
        panContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panContent.setBackground(AppColors.BG);
        setContentPane(panContent);

        panHeader = new JPanel(new FlowLayout());
        panHeader.setBackground(AppColors.TRANSPARENT);
        panHeader.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK),
                        "Novo Comentário",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new Font("Arial", Font.BOLD, 16)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        edtComment = new JTextArea();
        edtComment.setRows(3);
        edtComment.setLineWrap(true);
        edtComment.setWrapStyleWord(true);
        edtComment.setBackground(AppColors.FIELD_PINK);
        scrComment = new JScrollPane(edtComment);
        scrComment.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panCommentField = createCustomTextfield("Comentário", scrComment);
        panHeader.add(panCommentField);

        chkAnonimo = new JCheckBox("Anônimo");
        chkAnonimo.setFont(new Font("Arial", Font.PLAIN, 14));
        chkAnonimo.setBackground(AppColors.TRANSPARENT);
        panHeader.add(chkAnonimo);

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
        panHeader.add(btnPublish);

        panContent.add(panHeader, BorderLayout.NORTH);

        scrArea = new JScrollPane();
        scrArea.setBorder(null);
        panContent.add(scrArea, BorderLayout.CENTER);

        scrBar = scrArea.getVerticalScrollBar();
        scrBar.setBackground(AppColors.BUTTON_PINK);
        scrBar.setForeground(AppColors.BUTTON_PINK);
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

    private void initComments() {
        List<Comentario> comentarios = comentarioController.filtrarPorIdPublicacao(id);

        scrArea.setViewportView(null);

        if (comentarios.isEmpty()) {
            JPanel emptyPanel = new JPanel(new GridBagLayout());
            emptyPanel.setBackground(AppColors.TRANSPARENT);

            JLabel emptyLabel = new JLabel("Nenhum comentário encontrado");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            emptyPanel.add(emptyLabel);
            scrArea.setViewportView(emptyPanel);
        } else {
            panComments = new JPanel();
            panComments.setLayout(new BoxLayout(panComments, BoxLayout.Y_AXIS));
            panComments.setBackground(AppColors.TRANSPARENT);

            for (Comentario comentario : comentarios) {
                JPanel panComentario = createCommentComponent(comentario);
                panComments.add(panComentario);
            }

            scrArea.setViewportView(panComments);
            SwingUtilities.invokeLater(() -> scrArea.getViewport().setViewPosition(new Point(0, 0)));
        }

    }

    private JPanel createCommentComponent(Comentario comentario) {
        JPanel panPost = new JPanel();
        panPost.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panPost.setLayout(new BorderLayout());
        panPost.setBackground(AppColors.TRANSPARENT);

        JPanel panHeader = new JPanel();
        panHeader.setLayout(new BorderLayout());
        panHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panHeader.setBackground(AppColors.BG);

        JLabel lblUserPic = new JLabel();

        if (comentario.isAnonimo() && usuario instanceof Gestante) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/anon.png")));
        } else if (comentario.getAutor() instanceof Medico) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/doctor.png")));
        } else if (comentario.getAutor() instanceof Secretario) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/secretary.png")));
        } else if (comentario.getAutor() instanceof Admin) {
            lblUserPic = new JLabel(new ImageIcon(getClass().getResource("/images/adm.png")));
        } else if (comentario.getAutor() instanceof Gestante) {
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
        panInfo.setLayout(new GridLayout(3, 1));
        panInfo.setBackground(AppColors.BG);
        panInfo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel lblAuthor = new JLabel(comentario.getAutor().getNome());
        if (comentario.isAnonimo() && usuario instanceof Gestante) {
            lblAuthor.setText("Usuário Desconhecido");
        }
        lblAuthor.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTime = new JLabel(comentario.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        lblTime.setFont(new Font("Arial", Font.ITALIC, 12));

        JLabel lblSpecialist = new JLabel("");
        lblSpecialist.setFont(new Font("Arial", Font.BOLD, 12));
        lblSpecialist.setForeground(AppColors.BUTTON_PINK);

        if (comentario.getAutor() instanceof Medico) {
            lblSpecialist.setText("Comentário de Especialista");
        } else if (comentario.getAutor() instanceof Secretario || comentario.getAutor() instanceof Admin) {
            lblSpecialist.setText("Comentário da Moderação");
        }

        panInfo.add(lblAuthor);
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
        txtAreaBody.setText(comentario.getConteudo());

        JPanel panButtom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButtom.setBackground(AppColors.BG);

        if ((comentario.getAutor().getId() == usuario.getId()) || usuario instanceof Admin
                || usuario instanceof Secretario) {
            JButton btnDelete = new RoundedButton("Deletar Publicação", 10);
            btnDelete.setBackground(AppColors.BUTTON_PINK);
            btnDelete.setForeground(Color.WHITE);
            btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnDelete.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnDeleteActionPerformed(evt, comentario.getId());
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
            Publicacao publicacao = publicacaoController.buscarPorId(id);
            comentarioController.cadastrar(edtComment.getText(), LocalDateTime.now(), chkAnonimo.isSelected(),
                    publicacao,
                    usuario, null);
            edtComment.setText("");
            chkAnonimo.setSelected(false);
            JOptionPane.showMessageDialog(this, "Comentário realizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            initComments();
            this.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt, int id) {
        int option = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir esta publicação?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            comentarioController.excluir(id);
            initComments();
            this.repaint();
        }
    }

}
