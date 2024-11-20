package com.cantarino.souza.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.enums.StatusProcedimentos;

public class TMExame extends AbstractTableModel {

    private List<Exame> lista;

    private final int COL_ID = 0;
    private final int COL_PACIENTE = 1;
    private final int COL_DESCRICAO = 2;
    private final int COL_DATA = 3;
    private final int COL_VALOR = 4;
    private final int COL_STATUS = 5;
    private final int COL_RELATORIO = 6;
    private final int COL_DATA_RESULTADO = 7;
    private final int COL_REQUISITADO_POR = 8;
    private final int COL_LABORATORIO = 9;

    public TMExame(List<Exame> listaExames) {
        lista = listaExames;
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case COL_ID:
                return "ID";
            case COL_PACIENTE:
                return "Paciente";
            case COL_DESCRICAO:
                return "Descrição";
            case COL_DATA:
                return "Data";
            case COL_VALOR:
                return "Valor";
            case COL_STATUS:
                return "Status";
            case COL_RELATORIO:
                return "Relatório";
            case COL_DATA_RESULTADO:
                return "Data Resultado";
            case COL_REQUISITADO_POR:
                return "Requisitado Por";
            case COL_LABORATORIO:
                return "Laboratório";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exame aux = new Exame();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);

            switch (columnIndex) {
                case -1:
                    return aux;
                case COL_ID:
                    return aux.getId();
                case COL_PACIENTE:
                    return aux.getPaciente() != null ? aux.getPaciente().getNome() : "-";
                case COL_DESCRICAO:
                    return aux.getDescricao();
                case COL_DATA:
                    return aux.getData() != null ? aux.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                            : "-";
                case COL_VALOR:
                    return aux.getValor();
                case COL_STATUS:
                    return aux.getStatus();
                case COL_RELATORIO:
                    return aux.getRelatorio() == null ? "-" : "REGISTRADO";
                case COL_DATA_RESULTADO:
                    return aux.getDataResultado() == null ? "-"
                            : aux.getDataResultado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                case COL_REQUISITADO_POR:
                    return aux.getRequisitadoPor() != null ? aux.getRequisitadoPor().getNome() : "-";
                case COL_LABORATORIO:
                    return aux.getLaboratorio();
                default:
                    return null;
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public static class ExameTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (!isSelected) {
                TMExame model = (TMExame) table.getModel();
                Exame exame = model.lista.get(row);

                if (StatusProcedimentos.CANCELADA.getValue().equals(exame.getStatus())) {
                    c.setBackground(new Color(255, 200, 200));
                    setBackground(new Color(255, 200, 200));
                } else if (StatusProcedimentos.CONCLUIDA.getValue().equals(exame.getStatus())) {
                    c.setBackground(new Color(200, 255, 200));
                    setBackground(new Color(200, 255, 200));
                } else {
                    c.setBackground(Color.WHITE);
                    setBackground(Color.WHITE);
                }
            }

            setOpaque(true);
            return c;
        }
    }

    public static DefaultTableCellRenderer getCustomRenderer() {
        return new ExameTableCellRenderer();
    }

}
