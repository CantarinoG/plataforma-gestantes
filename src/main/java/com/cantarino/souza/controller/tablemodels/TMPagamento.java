package com.cantarino.souza.controller.tablemodels;

import com.cantarino.souza.model.entities.Pagamento;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMPagamento extends AbstractTableModel {
    private List<Pagamento> lista;
    private final int id = 0;
    private final int valor = 1;
    private final int registradoPor = 2;
    private final int paciente = 3;
    private final int metodoPagamento = 4;
    private final int procedimento = 5;

    public TMPagamento(List<Pagamento> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pagamento aux = new Pagamento();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case -1:
                    return aux;
                case id:
                    return aux.getId();
                case valor:
                    return aux.getValor();
                case registradoPor:
                    return aux.getRegistradoPor().getNome();
                case paciente:
                    return aux.getPaciente().getNome();
                case metodoPagamento:
                    return aux.getMetodoPagamento();
                case procedimento:
                    return aux.getProcedimento().getId() + " | " + aux.getProcedimento().getDescricao();
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
            case valor:
                return "Valor";
            case registradoPor:
                return "Registrado Por";
            case paciente:
                return "Paciente";
            case metodoPagamento:
                return "Método de Pagamento";
            case procedimento:
                return "Procedimento";
            default:
                return "";
        }
    }
}
