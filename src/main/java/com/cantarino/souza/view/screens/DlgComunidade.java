package com.cantarino.souza.view.screens;

import java.awt.*;

import javax.swing.*;

import com.cantarino.souza.view.components.*;

public class DlgComunidade extends JDialog {

    JPanel panBackground;
    JPanel panLeft;
    JLabel lblAction;
    JPanel panOptions;
    JButton btnTodos;
    JButton btnEspecialista;
    JButton btnMeus;
    JPanel panContent;
    JScrollPane scrArea;
    JScrollBar scrBar;

    public DlgComunidade(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        scrArea = new JScrollPane();
        scrArea.setBorder(null);
        panLeft.add(scrArea, gbc);

        scrBar = scrArea.getVerticalScrollBar();
        scrBar.setBackground(AppColors.BUTTON_PINK);
        scrBar.setForeground(AppColors.BUTTON_PINK);

    }

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnMeusActionPerformed(java.awt.event.ActionEvent evt) {

    }

}
