package br.com.mercadoon.api.exception;

public class ProdutoNotFoundException extends NotFound {
    public ProdutoNotFoundException(String msg) {
        super(msg);
    }
}
