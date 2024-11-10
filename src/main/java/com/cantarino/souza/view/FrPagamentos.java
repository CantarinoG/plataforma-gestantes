package com.cantarino.souza.view;

import java.awt.*;
import javax.swing.*;
import com.cantarino.souza.view.components.*;

public class FrPagamentos extends JFrame {

    JPanel panBackground;
    JPanel panHeader;
    JLabel lblTitle;
    JPanel panContent;
    JLabel lblAction;
    JPanel panTable;
    JPanel panOptions;
    JButton btnEmitirRecibo;

    public FrPagamentos() {
        initComponents();
    }

    private void initComponents() {
        setTitle("BemGestar | Pagamentos");
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        lblAction = new JLabel("Histórico de Pagamentos");
        lblAction.setFont(new Font("Arial", Font.BOLD, 64));
        lblAction.setForeground(AppColors.TITLE_BLUE);
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        panContent.add(lblAction, gbc);

        /*
         * panTable = new JPanel();
         * panTable.setBackground(AppColors.TITLE_BLUE);
         * gbc.gridy = 1;
         * gbc.weighty = 1.0;
         * gbc.fill = GridBagConstraints.BOTH;
         * panContent.add(panTable, gbc);
         */

        {// Tabela mockada
            String[] columnNames = { "Data", "Hora", "Médico", "Especialidade", "Status" };
            Object[][] data = {
                    { "2024-03-20", "14:30", "Dr. Silva", "Obstetrícia", "Agendada" },
                    { "2024-03-25", "10:00", "Dra. Santos", "Ginecologia", "Agendada" }
            };

            JTable tblConsultas = new JTable(data, columnNames);
            tblConsultas.setFillsViewportHeight(true);
            tblConsultas.setRowHeight(30);
            tblConsultas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

            JScrollPane scrollPane = new JScrollPane(tblConsultas);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panContent.add(scrollPane, gbc);
        }

        panOptions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panOptions.setBackground(AppColors.TRANSPARENT);
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panContent.add(panOptions, gbc);

        btnEmitirRecibo = new RoundedButton("Emitir Recibo", 50);
        btnEmitirRecibo.setPreferredSize(new Dimension(200, 55));
        btnEmitirRecibo.setMinimumSize(new Dimension(200, 55));
        btnEmitirRecibo.setMaximumSize(new Dimension(200, 55));
        btnEmitirRecibo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEmitirRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirReciboActionPerformed(evt);
            }
        });
        panOptions.add(btnEmitirRecibo);

        panBackground.add(panContent, BorderLayout.CENTER);
    }

    private void btnEmitirReciboActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Clicou no botão de emitir recibo!");
    }

}
