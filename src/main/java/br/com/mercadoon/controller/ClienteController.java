package br.com.mercadoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "cadastro_cliente";
    }

    @GetMapping("/cadastrado")
    public String cadastrado() {
        return "cliente_cadastrado";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
