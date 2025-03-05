package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.CartaoDto;
import br.com.mercadoon.api.entity.Cartao;
import br.com.mercadoon.api.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cartao", produces = { "application/json" })
@Tag(name = "Cartão")
public class CartaoRestController {

    private CartaoService cartaoService;

    @Autowired
    public CartaoRestController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @Operation(summary = "Lista todos os cartões", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartões listados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    @GetMapping
    public List<CartaoDto> listar() {
        return cartaoService.listar();
    }

    @Operation(summary = "Busca cartão por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartão encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    @GetMapping("/{id}")
    public CartaoDto buscar(@PathVariable Long id) {
        return cartaoService.buscar(id);
    }


    @Operation(summary = "Busca cartões por cliente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartões encontrados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/cliente/{clienteId}")
    public List<CartaoDto> buscarCartoesPorClienteId(@PathVariable Long clienteId) {
        return cartaoService.buscarCartoesPorClienteId(clienteId);
    }

    @Operation(summary = "Cadastra cartão", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartão cadastrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PostMapping(value = "/{clienteId}", consumes = { "application/json" })
    public ResponseEntity<CartaoDto> add(@PathVariable Long clienteId, @Valid @RequestBody Cartao cartao) {
        return new ResponseEntity<>(cartaoService.add(clienteId, cartao), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza cartão", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartão atualizado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    @PutMapping(value = "/{id}", consumes = { "application/json" })
    public CartaoDto atualiar(@PathVariable Long id, @Valid @RequestBody Cartao cartao) {
        return cartaoService.atualizar(id, cartao);
    }

    @Operation(summary = "Deleta cartão", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cartão deletado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cartaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta todos os cartões", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cartões deletados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        cartaoService.deletarTodos();
        return ResponseEntity.noContent().build();
    }

}
