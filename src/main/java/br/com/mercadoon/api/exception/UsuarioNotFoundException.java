package br.com.mercadoon.api.exception;

public class UsuarioNotFoundException extends NotFound {
    public UsuarioNotFoundException(String msg) {
        super(msg);
    }
}
