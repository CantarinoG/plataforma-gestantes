package com.cantarino.souza.view.screens;

import java.awt.*;

import javax.swing.*;
import com.cantarino.souza.view.components.*;

public class DlgAgendaGestante extends JDialog {

        private JPanel panBackground;
        private JPanel panHeader;
        private JButton btnConsultas;
        private JButton btnExames;
        private JPanel panContent;
        private JLabel lblTitle;

        public DlgAgendaGestante(JFrame parent, boolean modal) {
                super(parent, modal);
                initComponents();
        }

        private void initComponents() {
                setTitle("Agenda");
                setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                setSize(1920, 1080);
                setLocationRelativeTo(null);

                panBackground = new BackgroundPanel("/images/background.png");
                panBackground.setLayout(new BorderLayout());
                setContentPane(panBackground);

                panHeader = new JPanel();
                panHeader.setPreferredSize(new Dimension(getWidth(), 138));
                panHeader.setBorder(BorderFactory.createEmptyBorder(10, 64, 10, 64));
                panHeader.setBackground(AppColors.BUTTON_PINK);
                panHeader.setOpaque(true);
                panHeader.setLayout(new GridBagLayout());

                lblTitle = new JLabel("Minha Agenda");
                lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
                lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
                lblTitle.setForeground(AppColors.TITLE_BLUE);

                btnConsultas = new RoundedButton("Consultas", 10);
                btnConsultas.setPreferredSize(new Dimension(150, 50));
                btnConsultas.setBackground(Color.WHITE);
                btnConsultas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnConsultas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnConsultasActionPerformed(evt);
                        }
                });

                btnExames = new RoundedButton("Exames", 10);
                btnExames.setPreferredSize(new Dimension(150, 50));
                btnExames.setBackground(Color.WHITE);
                btnExames.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnExames.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnExamesActionPerformed(evt);
                        }
                });

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(0, 10, 0, 10);
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.CENTER;
                panHeader.add(lblTitle, gbc);

                gbc.gridwidth = 1;
                gbc.gridy = 1;
                panHeader.add(btnConsultas, gbc);
                panHeader.add(btnExames, gbc);

                panBackground.add(panHeader, BorderLayout.NORTH);
                panContent = new PanConsultasAgendadas();
                panBackground.add(panContent, BorderLayout.CENTER);
        }

        private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {
                panBackground.remove(panContent);
                panContent = new PanConsultasAgendadas();
                panBackground.add(panContent, BorderLayout.CENTER);
                panBackground.revalidate();
                panBackground.repaint();
        }

        private void btnExamesActionPerformed(java.awt.event.ActionEvent evt) {
                panBackground.remove(panContent);
                panContent = new PanExamesAgendados();
                panBackground.add(panContent, BorderLayout.CENTER);
                panBackground.revalidate();
                panBackground.repaint();
        }

}
