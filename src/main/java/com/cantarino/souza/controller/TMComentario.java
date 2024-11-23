package com.cantarino.souza.controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.cantarino.souza.model.entities.Comentario;

public class TMComentario extends AbstractTableModel {
    private List<Comentario> lista;
    private final int id = 0;
    private final int conteudo = 1;
    private final int data = 2;
    private final int publicacao = 3;
    private final int autor = 4;

    public TMComentario(List<Comentario> listaComentarios) {
        lista = listaComentarios;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case id:
                return "ID";
            case conteudo:
                return "Conteúdo";
            case data:
                return "Data";
            case publicacao:
                return "Publicacao";
            case autor:
                return "Autor";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Comentario aux = new Comentario();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case id:
                    return aux.getId();
                case conteudo:
                    return aux.getConteudo();
                case data:
                    return aux.getData();
                case publicacao:
                    return aux.getPublicacao();
                case autor:
                    return aux.getAutor();
                default:
                    return null;
            }
        }
    }

}
