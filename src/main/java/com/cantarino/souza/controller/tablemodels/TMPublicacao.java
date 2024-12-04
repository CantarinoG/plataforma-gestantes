package com.cantarino.souza.controller.tablemodels;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.cantarino.souza.controller.tablemodels.TMExame.ExameTableCellRenderer;
import com.cantarino.souza.model.entities.Publicacao;

public class TMPublicacao extends AbstractTableModel {
    private List<Publicacao> lista;
    private final int id = 0;
    private final int titulo = 1;
    private final int corpo = 2;
    private final int data = 3;
    private final int autor = 4;

    public TMPublicacao(List<Publicacao> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Publicacao aux = new Publicacao();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case -1:
                    return aux;
                case id:
                    return aux.getId();
                case titulo:
                    return aux.getTitulo();
                case corpo:
                    return aux.getCorpo();
                case data:
                    return aux.getData().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                case autor:
                    return "(" + aux.getAutor().getId() + ") " + aux.getAutor().getNome();
                default:
                    return null;
            }
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case id:
                return "ID";
            case titulo:
                return "Título";
            case corpo:
                return "Corpo do Texto";
            case data:
                return "Data";
            case autor:
                return "Autor";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public static DefaultTableCellRenderer getCustomRenderer() {
        return new ExameTableCellRenderer();
    }

    public static class PublicacaoTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (!isSelected) {
                c.setBackground(java.awt.Color.WHITE);
                setBackground(java.awt.Color.WHITE);
            }

            setOpaque(true);
            return c;
        }
    }

}
