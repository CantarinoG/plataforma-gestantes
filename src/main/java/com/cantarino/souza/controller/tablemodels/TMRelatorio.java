package com.cantarino.souza.controller.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.cantarino.souza.model.entities.Relatorio;

public class TMRelatorio extends AbstractTableModel {
    private List<Relatorio> lista;
    private final int id = 0;
    private final int resultado = 1;
    private final int obeservacoes = 2;

    public TMRelatorio(List<Relatorio> lista) {
        this.lista = lista;
    }

    @Override
    public int getColumnCount() {
        return lista.size();
    }

    @Override
    public int getRowCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Relatorio aux = new Relatorio();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case -1:
                    return aux;
                case id:
                    return aux.getId();
                case resultado:
                    return aux.getResultado();
                case obeservacoes:
                    return aux.getObeservacoes();
                default:
                    return null;
            }
        }
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case id:
                return "ID";
            case resultado:
                return "Resultado";
            case obeservacoes:
                return "Observações";
            default:
                return null;
        }
    }
}
