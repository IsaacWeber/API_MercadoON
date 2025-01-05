package br.com.mercadoon.api.exception;

public class LoginException extends RuntimeException {
    public LoginException(String msg) {
        super(msg);
    }
}
