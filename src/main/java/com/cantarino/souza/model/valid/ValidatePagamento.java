package com.cantarino.souza.model.valid;

import com.cantarino.souza.model.entities.Pagamento;
import com.cantarino.souza.model.exceptions.PagamentoException;

public class ValidatePagamento {
    public Pagamento validaCamposEntrada(String valor, String metodoPagamento) {
        Pagamento pagamento = new Pagamento();
        if (valor == null || valor.isEmpty())
            throw new PagamentoException("ERRO: Campo valor não pode ser vazio.");
        double validValor;
        try {
            valor = valor.replace(",", ".");
            validValor = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new PagamentoException("ERRO: Campo valor deve conter um número válido.");
        }
        if (validValor <= 0) {
            throw new PagamentoException("ERRO: Valor deve ser maior que zero.");
        }
        pagamento.setValor(validValor);

        if (metodoPagamento == null || metodoPagamento.isEmpty())
            throw new PagamentoException("ERRO: Campo de metodo de pagamento não pode estar vazio.");
        pagamento.setMetodoPagamento(metodoPagamento);

        return pagamento;

    }
}
