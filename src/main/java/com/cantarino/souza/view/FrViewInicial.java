package com.cantarino.souza.view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import com.cantarino.souza.components.*;

public class FrViewInicial extends JFrame {

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
    JLabel lblImageRight;

    public FrViewInicial() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Início");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
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
                        + "x;'>Bem-vinda ao BemGestar</div></html>");
        lblAction.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        panInnerLeft.add(lblAction);
        panInnerLeft.add(Box.createRigidArea(new Dimension(0, 20)));

        lblDescription = new JLabel(
                "<html><div style='text-align: center; width: " + panInnerLeft.getWidth()
                        + "x;'>Estamos aqui para acompanhar você em cada etapa dessa jornada única, oferecendo suporte integral durante toda a sua gestação. Com o BemGestar, você pode gerenciar facilmente consultas, exames e se conectar com outras gestantes para compartilhar experiências e dicas valiosas. Crie sua conta e faça parte dessa comunidade que entende o valor do cuidado e acolhimento.</div></html>");
        lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        panInnerLeft.add(lblDescription);
        panInnerLeft.add(Box.createRigidArea(new Dimension(0, 20)));

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(AppColors.TRANSPARENT);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton btnCriarConta = new RoundedButton("Criar Conta", 50);
        btnCriarConta.setBackground(AppColors.BUTTON_PINK);
        btnCriarConta.setForeground(Color.WHITE);
        btnCriarConta.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(btnCriarConta);
        btnCriarConta.setOpaque(true);
        btnCriarConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCriarConta.addActionListener(evt -> btnCriarContaActionPerformed(evt));

        RoundedButton btnConferirDicas = new RoundedButton("Conferir Dicas", 50);
        btnConferirDicas.setBackground(AppColors.TRANSPARENT);
        btnConferirDicas.setForeground(AppColors.BUTTON_PINK);
        btnConferirDicas.setBorder(BorderFactory.createLineBorder(new Color(255, 153, 153), 1));
        btnConferirDicas.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(btnConferirDicas);
        btnConferirDicas.setOpaque(true);
        btnConferirDicas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConferirDicas.addActionListener(evt -> btnConferirDicasActionPerformed(evt));
        
        panInnerLeft.add(buttonPanel);
        panInnerLeft.add(Box.createRigidArea(new Dimension(0, 20)));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        panLeft.add(panInnerLeft, gbc);
        
        lblImageRight = new JLabel(new ImageIcon(getClass().getResource("/images/maternitypose.png")));
        lblImageRight.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        panRight.add(lblImageRight, gbc);
    }
        
    private void btnConferirDicasActionPerformed(ActionEvent evt) {
        System.out.println("Clicou em Conferir Dicas");
    }
        
    private void btnCriarContaActionPerformed(ActionEvent evt) {
        System.out.println("Clicou em Criar Conta");
    }
}
