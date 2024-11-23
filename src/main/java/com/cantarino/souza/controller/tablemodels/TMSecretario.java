package com.cantarino.souza.controller.tablemodels;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.cantarino.souza.model.entities.Secretario;

public class TMSecretario extends AbstractTableModel {

    private List<Secretario> lista;

    private final int id = 0;
    private final int cpf = 1;
    private final int nome = 2;
    private final int email = 3;
    private final int dataNascimento = 4;
    private final int telefone = 5;
    private final int endereco = 6;
    private final int dataContratacao = 7;

    public TMSecretario(List<Secretario> listaSecretarios) {
        lista = listaSecretarios;
    }

    @Override
    public int getColumnCount() {
        return 8;
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
            case cpf:
                return "CPF";
            case nome:
                return "Nome";
            case email:
                return "Email";
            case dataNascimento:
                return "Data de Nascimento";
            case telefone:
                return "Telefone";
            case endereco:
                return "Endereço";
            case dataContratacao:
                return "Data de Contratação";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Secretario aux = new Secretario();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);

            switch (columnIndex) {
                case -1:
                    return aux;
                case id:
                    return aux.getId();
                case cpf:
                    return aux.getCpf();
                case nome:
                    return aux.getNome();
                case email:
                    return aux.getEmail();
                case dataNascimento:
                    return aux.getDataNascimento() != null
                            ? aux.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            : "-";
                case telefone:
                    return aux.getTelefone();
                case endereco:
                    return aux.getEndereco();
                case dataContratacao:
                    return aux.getDataContratacao() != null
                            ? aux.getDataContratacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            : "-";
                default:
                    return null;
            }
        }
    }

}
