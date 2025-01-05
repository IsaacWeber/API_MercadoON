package br.com.mercadoon.domain.controller;

import br.com.mercadoon.api.dto.ProdutoDto;
import br.com.mercadoon.api.service.ProdutoService;
import br.com.mercadoon.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/cadastrar")
    public String adicionar() {
        return "cadastro_produto";
    }

    @GetMapping("/exibir/{id}")
    public ModelAndView exibir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("produto");
        mv.addObject("produto", produtoService.buscar(id));
        mv.addObject("imageUtil", new ImageUtil());
        return mv;
    }

}
