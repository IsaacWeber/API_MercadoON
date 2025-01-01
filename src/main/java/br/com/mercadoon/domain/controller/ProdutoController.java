package br.com.mercadoon.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    @GetMapping("/cadastrar")
    public String adicionar() {
        return "cadastro_produto";
    }

    @GetMapping("/cadastrado")
    public String cadastrado() {
        return "produto_cadastrado";
    }
}
