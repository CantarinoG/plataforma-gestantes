package com.cantarino.souza.view.screens;

import java.awt.*;

import javax.swing.*;
import com.cantarino.souza.view.components.*;

public class DlgAgendaGestante extends JDialog {

        private JPanel panFundo;
        private JPanel panHeader;
        private JButton btnConsultas;
        private JButton btnExames;
        private JPanel panConteudo;
        private JLabel lblTitulo;

        public DlgAgendaGestante(JFrame parent, boolean modal) {
                super(parent, modal);
                initComponents();
        }

        private void initComponents() {
                setTitle("Agenda");
                setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                setSize(1920, 1080);
                setLocationRelativeTo(null);

                panFundo = new BackgroundPanel("/images/background.png");
                panFundo.setLayout(new BorderLayout());
                setContentPane(panFundo);

                panHeader = new JPanel();
                panHeader.setPreferredSize(new Dimension(getWidth(), 138));
                panHeader.setBorder(BorderFactory.createEmptyBorder(10, 64, 10, 64));
                panHeader.setBackground(AppColors.BUTTON_PINK);
                panHeader.setOpaque(true);
                panHeader.setLayout(new GridBagLayout());

                lblTitulo = new JLabel("Minha Agenda");
                lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
                lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
                lblTitulo.setForeground(AppColors.TITLE_BLUE);

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
                panHeader.add(lblTitulo, gbc);

                gbc.gridwidth = 1;
                gbc.gridy = 1;
                panHeader.add(btnConsultas, gbc);
                panHeader.add(btnExames, gbc);

                panFundo.add(panHeader, BorderLayout.NORTH);
                panConteudo = new PanConsultasAgendadas();
                panFundo.add(panConteudo, BorderLayout.CENTER);
        }

        private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {
                panFundo.remove(panConteudo);
                panConteudo = new PanConsultasAgendadas();
                panFundo.add(panConteudo, BorderLayout.CENTER);
                panFundo.revalidate();
                panFundo.repaint();
        }

        private void btnExamesActionPerformed(java.awt.event.ActionEvent evt) {
                panFundo.remove(panConteudo);
                panConteudo = new PanExamesAgendados();
                panFundo.add(panConteudo, BorderLayout.CENTER);
                panFundo.revalidate();
                panFundo.repaint();
        }

}
