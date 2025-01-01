package br.com.mercadoon.api.enumeration;

public enum StatusCompra {
    EM_PROCESSO(0),
    EM_ENTREGA(1),
    ENTREGUE(2),
    CANCELADO(3);

    final int value;

    StatusCompra(int value) {
        this.value = value;
    }

}
