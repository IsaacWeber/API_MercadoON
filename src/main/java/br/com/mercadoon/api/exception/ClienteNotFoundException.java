package br.com.mercadoon.api.exception;

public class ClienteNotFoundException extends NotFound {
    public ClienteNotFoundException(String msg) {
        super(msg);
    }
}
