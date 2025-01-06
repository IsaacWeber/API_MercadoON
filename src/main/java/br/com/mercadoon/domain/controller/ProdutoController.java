package br.com.mercadoon.domain.controller;

import br.com.mercadoon.api.dto.ClienteDto;
import br.com.mercadoon.api.dto.ProdutoDto;
import br.com.mercadoon.api.entity.Cliente;
import br.com.mercadoon.api.service.ClienteService;
import br.com.mercadoon.api.service.ProdutoService;
import br.com.mercadoon.util.ImageUtil;
import jakarta.servlet.http.HttpSession;
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
    private ClienteService clienteService;

    public ProdutoController(ClienteService clienteService, ProdutoService produtoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    @GetMapping("/cadastrar")
    public ModelAndView adicionar(HttpSession session) {

        if(session.getAttribute("cliente") == null) {
            return new ModelAndView("erro");
        }

        ClienteDto clienteDto = (ClienteDto)session.getAttribute("cliente");

        session.setAttribute("cliente", clienteService.buscar(clienteDto.getId())); // Atualiza cliente na secao

        ModelAndView mv = new ModelAndView("cadastro_produto");
        mv.addObject("cliente", clienteDto);

        return mv;
    }

    @GetMapping("/exibir/{id}")
    public ModelAndView exibir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("produto");
        mv.addObject("produto", produtoService.buscar(id));
        mv.addObject("imageUtil", new ImageUtil());
        return mv;
    }

}
