package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.ProdutoDto;
import br.com.mercadoon.api.entity.Produto;
import br.com.mercadoon.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{id}")
    public ProdutoDto buscar(@PathVariable Long id) {
        return produtoService.buscar(id);
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<ProdutoDto> add(@PathVariable Long clienteId, @Valid @RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.add(clienteId, produto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ProdutoDto atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        produtoService.deletarTodos();
        return ResponseEntity.noContent().build();
    }

}
