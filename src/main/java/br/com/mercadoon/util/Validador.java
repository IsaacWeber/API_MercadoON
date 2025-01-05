package br.com.mercadoon.util;

import org.hibernate.annotations.CompositeTypeRegistration;
import org.springframework.stereotype.Component;

public final class Validador {

    public static boolean emailValido(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

}
