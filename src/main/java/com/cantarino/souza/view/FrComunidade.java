package com.cantarino.souza.view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import com.cantarino.souza.components.*;

public class FrComunidade extends JFrame {

    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JPanel panLeft;
    JPanel panInnerLeft;
    JPanel panRight;
    JLabel lblAction;
    JLabel lblDescription;
    JLabel lblImage;
    JLabel lblType;
    JPanel panOptions;
    JButton btnAll;
    JButton btnSpecialist;
    JButton btnMine;
    JPanel panPosts;
    JScrollPane scrArea;

    public FrComunidade() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Comunidade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new BorderLayout());
        setContentPane(panBackground);

        panHeader = new JPanel();
        panHeader.setLayout(new BorderLayout());
        panHeader.setBackground(AppColors.DARKER);
        panHeader.setPreferredSize(new Dimension(getWidth(), 74));
        panHeader.setBorder(BorderFactory.createEmptyBorder(15, 64, 15, 64));
        panBackground.add(panHeader, BorderLayout.NORTH);

        lblTitle = new JLabel("BemGestar");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        panHeader.add(lblTitle, BorderLayout.WEST);

        panContent = new JPanel();
        panContent.setLayout(new GridBagLayout());
        panContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panContent.setBackground(AppColors.TRANSPARENT);
        panBackground.add(panContent, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 10);
        panLeft = new JPanel();
        panLeft.setBackground(AppColors.TRANSPARENT);
        panLeft.setPreferredSize(new Dimension(0, 0));
        panLeft.setLayout(new GridBagLayout());
        panContent.add(panLeft, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(0, 0, 0, 0);
        panRight = new JPanel();
        panRight.setBackground(AppColors.TRANSPARENT);
        panRight.setPreferredSize(new Dimension(0, 0));
        panRight.setLayout(new GridBagLayout());
        panContent.add(panRight, gbc);

        panInnerLeft = new JPanel();
        panInnerLeft.setLayout(new BoxLayout(panInnerLeft, BoxLayout.Y_AXIS));
        panInnerLeft.setBackground(AppColors.TRANSPARENT);

        lblAction = new JLabel(
                "<html><div style='text-align: center; width: " + panInnerLeft.getWidth()
                        + "x;'>Bem-vinda à comunidade do BemGestar</div></html>");
        lblAction.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        panInnerLeft.add(lblAction);
        panInnerLeft.add(Box.createRigidArea(new Dimension(0, 20)));

        lblDescription = new JLabel(
                "<html><div style='text-align: center; width: " + panInnerLeft.getWidth()
                        + "x;'>Bem-vinda ao nosso espaço dedicado a você! Aqui, futuras mamães podem compartilhar suas experiências, trocar dicas e tirar dúvidas sobre essa fase tão especial e cheia de descobertas. Do início da gestação até o pós-parto, nosso fórum oferece um ambiente seguro e acolhedor, onde você pode conversar com outras grávidas, buscar apoio e dividir alegrias e desafios. Sinta-se à vontade para fazer perguntas, contar sua história e aprender com a comunidade. Juntas, vamos tornar essa jornada mais leve e cheia de suporte!</div></html>");
        lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        panInnerLeft.add(lblDescription);
        panInnerLeft.add(Box.createRigidArea(new Dimension(0, 20)));

        panInnerLeft.add(Box.createRigidArea(new Dimension(0, 20)));

        lblImage = new JLabel(new ImageIcon(getClass().getResource("/images/community.png")));
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        panInnerLeft.add(lblImage);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        panLeft.add(panInnerLeft, gbc);

        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        lblType = new JLabel("Tipos de publicação:");
        lblType.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.weighty = 0;
        panRight.add(lblType, gbc);

        panOptions = new JPanel(new GridLayout(1, 3, 10, 0));
        gbc.gridy = 1;
        gbc.weighty = 0;
        panRight.add(panOptions, gbc);

        btnAll = new RoundedButton("Todos", 50);
        btnAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });
        panOptions.add(btnAll);

        btnSpecialist = new RoundedButton("De Especialistas", 50);
        btnSpecialist.setPreferredSize(new Dimension(0, 50));
        btnSpecialist.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSpecialist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpecialistActionPerformed(evt);
            }
        });
        panOptions.add(btnSpecialist);

        btnMine = new RoundedButton("Meus", 50);
        btnMine.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMineActionPerformed(evt);
            }
        });
        panOptions.add(btnMine);

        panPosts = new JPanel();
        panPosts.setBackground(AppColors.TRANSPARENT);

        scrArea = new JScrollPane(panPosts);
        scrArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrArea.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panRight.add(scrArea, gbc);

        panPosts.add(new JLabel("Posts virão aqui"));
    }

    private void btnAllActionPerformed(ActionEvent evt) {
        System.out.println("Clicou em todos");
    }

    private void btnSpecialistActionPerformed(ActionEvent evt) {
        System.out.println("Clicou nos especialistas");
    }

    private void btnMineActionPerformed(ActionEvent evt) {
        System.out.println("Clicou nos meus");
    }

}
