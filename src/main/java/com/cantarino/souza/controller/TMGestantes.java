package com.cantarino.souza.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.cantarino.souza.model.entities.Gestante;

public class TMGestantes extends AbstractTableModel {

    private List<Gestante> lista;

    private final int id = 0;
    private final int cpf = 1;
    private final int nome = 2;
    private final int email = 3;
    private final int dataNascimento = 4;
    private final int telefone = 5;
    private final int endereco = 6;
    private final int previsaoParto = 7;
    private final int contatoEmergencia = 8;
    private final int historicoMedico = 9;
    private final int tipoSanguineo = 10;

    public TMGestantes(List<Gestante> listaGestantes) {
        lista = listaGestantes;
    }

    @Override
    public int getColumnCount() {
        return 11;
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
            case previsaoParto:
                return "Previsão do Parto";
            case contatoEmergencia:
                return "Contato de Emergência";
            case historicoMedico:
                return "Histórico Médico";
            case tipoSanguineo:
                return "Tipo Sanguíneo";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gestante aux = new Gestante();
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
                case previsaoParto:
                    return aux.getPrevisaoParto() != null
                            ? aux.getPrevisaoParto().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            : "-";
                case contatoEmergencia:
                    return aux.getContatoEmergencia();
                case historicoMedico:
                    return aux.getHistoricoMedico();
                case tipoSanguineo:
                    return aux.getTipoSanguineo();
                default:
                    return null;
            }
        }
    }

}
