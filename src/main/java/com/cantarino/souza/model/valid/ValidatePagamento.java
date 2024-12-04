package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.entities.Procedimento;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.model.exceptions.PagamentoException;

public class ValidatePagamento {
    public Pagamento validaCamposEntrada(String valor, String metodoPagamento, Usuario registradoPor,
            Gestante paciente, Procedimento procedimento) {
        Pagamento pagamento = new Pagamento();
        if (valor == null || valor.isEmpty())
            throw new PagamentoException("ERRO: Campo valor não pode ser vazio.");
        double valorConvertido;
        try {
            valor = valor.replace(",", ".");
            valorConvertido = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new PagamentoException("ERRO: Campo valor deve conter um número válido.");
        }
        if (valorConvertido <= 0) {
            throw new PagamentoException("ERRO: Valor deve ser maior que zero.");
        }
        pagamento.setValor(valorConvertido);

        if (metodoPagamento == null || metodoPagamento.isEmpty())
            throw new PagamentoException("ERRO: Campo de metodo de pagamento não pode estar vazio.");
        pagamento.setMetodoPagamento(metodoPagamento);

        if (registradoPor == null) {
            throw new PagamentoException("ERRO: Usuário que registrou não pode ser nulo");
        }
        pagamento.setRegistradoPor(registradoPor);

        if (paciente == null) {
            throw new PagamentoException("ERRO: Paciente não pode ser nulo");
        }
        pagamento.setPaciente(paciente);

        if (procedimento == null) {
            throw new PagamentoException("ERRO: Procedimento não pode ser nulo");
        }
        if (procedimento.getStatus().equals(StatusProcedimentos.CANCELADA.getValor())) {
            throw new PagamentoException("ERRO: Não é possível registrar pagamento para um procedimento cancelado");
        }
        pagamento.setProcedimento(procedimento);

        if (valorConvertido > procedimento.getValor()) {
            throw new PagamentoException("ERRO: O valor informado não pode ser maior que o valor do procedimento");
        }

        return pagamento;

    }
}