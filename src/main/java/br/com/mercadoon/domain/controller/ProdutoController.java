package br.com.mercadoon.domain.controller;

import br.com.mercadoon.api.dto.ProdutoDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    @GetMapping("/cadastrar")
    public String adicionar() {
        return "cadastro_produto";
    }

    @GetMapping("/cadastrado")
    public ModelAndView cadastrado(@ModelAttribute("produto")ProdutoDto produtoDto) {
        ModelAndView mv = new ModelAndView("produto_cadastrado");
        mv.addObject("produto", produtoDto);
        return mv;
    }
}
