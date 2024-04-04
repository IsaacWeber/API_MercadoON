package br.com.mercadoon.rest;

import br.com.mercadoon.dto.ProdutoDto;
import br.com.mercadoon.entity.Produto;
import br.com.mercadoon.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoRestController {
    private ProdutoService produtoService;

    @Autowired
    public ProdutoRestController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoDto> listar() {
        return produtoService.listar();
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> add(@Valid @RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.add(produto), HttpStatus.CREATED);
    }
}
