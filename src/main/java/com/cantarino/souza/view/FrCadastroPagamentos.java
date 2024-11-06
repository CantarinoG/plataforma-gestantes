package com.cantarino.souza.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cantarino.souza.components.AppColors;
import com.cantarino.souza.components.BackgroundPanel;
import com.cantarino.souza.components.RoundedButton;

import javax.swing.JLabel;

public class FrCadastroPagamentos extends JFrame {
    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JLabel lblAction;
    JPanel panButton;
    RoundedButton btnCadastrarPagamento;

    public FrCadastroPagamentos() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Cadastrar Pagamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panBackground = new BackgroundPanel("/images/background.png");
        panBackground.setLayout(new BorderLayout());
        setContentPane(panBackground);

        panHeader = new JPanel();
        panHeader.setLayout(new BorderLayout());
        panHeader.setBackground(AppColors.DARKER);
        panHeader.setPreferredSize(new Dimension(getWidth(), 74));
        panBackground.add(panHeader, BorderLayout.NORTH);

        lblTitle = new JLabel("Bem Gestar");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        panHeader.add(lblTitle, BorderLayout.WEST);
        
    }
}
