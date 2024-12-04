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
    private JPanel panComentarios;
    private JPanel panHeader;
    private JTextArea edtComentario;
    private JScrollPane scrComentario;
    private JPanel panComentario;
    private JCheckBox chkAnonimo;
    private JButton btnPublicar;
    private JPanel panConteudo;

    private int id;

    private ComentarioController comentarioController;
    private PublicacaoController publicacaoController;
    private AutenticacaoController autenticacaoController;

    private Usuario usuario = null;

    public DlgComentarios(JDialog parent, boolean modal, int postId) {
        super(parent, modal);

        autenticacaoController = new AutenticacaoController();
        this.id = postId;
        comentarioController = new ComentarioController();
        publicacaoController = new PublicacaoController();
        usuario = autenticacaoController.getUsuario();

        initComponents();
        initComentarios();
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

        panConteudo = new JPanel(new BorderLayout());
        panConteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panConteudo.setBackground(AppColors.BG);
        setContentPane(panConteudo);

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

        edtComentario = new JTextArea();
        edtComentario.setRows(3);
        edtComentario.setLineWrap(true);
        edtComentario.setWrapStyleWord(true);
        edtComentario.setBackground(AppColors.FIELD_PINK);
        scrComentario = new JScrollPane(edtComentario);
        scrComentario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panComentario = criarTextFieldCustomizado("Comentário", scrComentario);
        panHeader.add(panComentario);

        chkAnonimo = new JCheckBox("Anônimo");
        chkAnonimo.setFont(new Font("Arial", Font.PLAIN, 14));
        chkAnonimo.setBackground(AppColors.TRANSPARENT);
        panHeader.add(chkAnonimo);

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
        panHeader.add(btnPublicar);

        panConteudo.add(panHeader, BorderLayout.NORTH);

        scrArea = new JScrollPane();
        scrArea.setBorder(null);
        panConteudo.add(scrArea, BorderLayout.CENTER);

        scrBar = scrArea.getVerticalScrollBar();
        scrBar.setBackground(AppColors.BUTTON_PINK);
        scrBar.setForeground(AppColors.BUTTON_PINK);
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
        }

        textField.setBackground(AppColors.FIELD_PINK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        fieldPanel.add(textField, BorderLayout.CENTER);

        return fieldPanel;
    }

    private void initComentarios() {
        List<Comentario> comentarios = comentarioController.buscarPorPublicacao(id);

        scrArea.setViewportView(null);

        if (comentarios.isEmpty()) {
            JPanel panVazio = new JPanel(new GridBagLayout());
            panVazio.setBackground(AppColors.TRANSPARENT);

            JLabel lblVazio = new JLabel("Nenhum comentário encontrado");
            lblVazio.setFont(new Font("Arial", Font.PLAIN, 16));

            panVazio.add(lblVazio);
            scrArea.setViewportView(panVazio);
        } else {
            panComentarios = new JPanel();
            panComentarios.setLayout(new BoxLayout(panComentarios, BoxLayout.Y_AXIS));
            panComentarios.setBackground(AppColors.TRANSPARENT);

            for (Comentario comentario : comentarios) {
                JPanel panComentario = criarComponenteComentario(comentario);
                panComentarios.add(panComentario);
            }

            scrArea.setViewportView(panComentarios);
            SwingUtilities.invokeLater(() -> scrArea.getViewport().setViewPosition(new Point(0, 0)));
        }

    }

    private JPanel criarComponenteComentario(Comentario comentario) {
        JPanel panPost = new JPanel();
        panPost.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panPost.setLayout(new BorderLayout());
        panPost.setBackground(AppColors.TRANSPARENT);

        JPanel panHeader = new JPanel();
        panHeader.setLayout(new BorderLayout());
        panHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panHeader.setBackground(AppColors.BG);

        JLabel lblIconeUsuario = new JLabel();

        if (comentario.isAnonimo() && usuario instanceof Gestante) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/anon.png")));
        } else if (comentario.getAutor() instanceof Medico) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/doctor.png")));
        } else if (comentario.getAutor() instanceof Secretario) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/secretary.png")));
        } else if (comentario.getAutor() instanceof Admin) {
            lblIconeUsuario = new JLabel(new ImageIcon(getClass().getResource("/images/adm.png")));
        } else if (comentario.getAutor() instanceof Gestante) {
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
        panInfo.setLayout(new GridLayout(3, 1));
        panInfo.setBackground(AppColors.BG);
        panInfo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel lblAutor = new JLabel(comentario.getAutor().getNome());
        if (comentario.isAnonimo() && usuario instanceof Gestante) {
            lblAutor.setText("Usuário Desconhecido");
        }
        lblAutor.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTempo = new JLabel(comentario.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        lblTempo.setFont(new Font("Arial", Font.ITALIC, 12));

        JLabel lblEspecial = new JLabel("");
        lblEspecial.setFont(new Font("Arial", Font.BOLD, 12));
        lblEspecial.setForeground(AppColors.BUTTON_PINK);

        if (comentario.getAutor() instanceof Medico) {
            lblEspecial.setText("Comentário de Especialista");
        } else if (comentario.getAutor() instanceof Secretario || comentario.getAutor() instanceof Admin) {
            lblEspecial.setText("Comentário da Moderação");
        }

        panInfo.add(lblAutor);
        panInfo.add(lblTempo);
        panInfo.add(lblEspecial);

        panHeader.add(panInfo, BorderLayout.CENTER);

        JTextArea txtCorpo = new JTextArea();
        txtCorpo.setEditable(false);
        txtCorpo.setWrapStyleWord(true);
        txtCorpo.setLineWrap(true);
        txtCorpo.setFont(new Font("Arial", Font.PLAIN, 14));
        txtCorpo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtCorpo.setBackground(AppColors.BG);
        txtCorpo.setText(comentario.getConteudo());

        JPanel panBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panBotoes.setBackground(AppColors.BG);

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
            panBotoes.add(btnDelete);
        }

        panPost.add(panHeader, BorderLayout.NORTH);
        panPost.add(txtCorpo, BorderLayout.CENTER);
        panPost.add(panBotoes, BorderLayout.SOUTH);

        return panPost;
    }

    private void btnPublicarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Publicacao publicacao = publicacaoController.buscar(id);
            comentarioController.salvar(edtComentario.getText(), LocalDateTime.now(), chkAnonimo.isSelected(),
                    publicacao,
                    usuario, null);
            edtComentario.setText("");
            chkAnonimo.setSelected(false);
            JOptionPane.showMessageDialog(this, "Comentário realizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            initComentarios();
            this.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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
            comentarioController.deletar(id);
            initComentarios();
            this.repaint();
        }
    }

}
