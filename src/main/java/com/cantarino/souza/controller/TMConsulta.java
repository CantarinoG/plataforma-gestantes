package com.cantarino.souza.controller;

import java.util.List;
import java.time.format.DateTimeFormatter;

import javax.swing.table.AbstractTableModel;
import com.cantarino.souza.model.entities.Consulta;

public class TMConsulta extends AbstractTableModel {

    private List<Consulta> lista;

    private final int COL_ID = 0;
    private final int COL_PACIENTE = 1;
    private final int COL_DESCRICAO = 2;
    private final int COL_DATA = 3;
    private final int COL_VALOR = 4;
    private final int COL_STATUS = 5;
    private final int COL_RELATORIO = 6;
    private final int COL_MEDICO = 7;
    private final int COL_DATA_RETORNO = 8;

    public TMConsulta(List<Consulta> listaConsultas) {
        lista = listaConsultas;
    }

    @Override
    public int getColumnCount() {
        return 9;
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
            case COL_MEDICO:
                return "Médico";
            case COL_DATA_RETORNO:
                return "Data Retorno";
            default:
                return "";
        }
    }

    @Override
    // TODO: Alguns valores não vão poder ser null, mudar depois
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta aux = new Consulta();
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
                case COL_MEDICO:
                    return aux.getMedico() != null ? aux.getMedico().getNome() : "-";
                case COL_DATA_RETORNO:
                    return aux.getDataRetorno() == null ? "-"
                            : aux.getDataRetorno().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                default:
                    return null;
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
