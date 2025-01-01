package br.com.mercadoon.api.exception;

public class NotFound extends RuntimeException {
    public NotFound(String msg) {
        super(msg);
    }
}
