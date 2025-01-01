package br.com.mercadoon.api.enumeration;

public enum FuncaoCartao {
    DEBITO(0),
    CREDITO(1);

    final int valor;

    FuncaoCartao(int valor) {
        this.valor = valor;
    }
}
