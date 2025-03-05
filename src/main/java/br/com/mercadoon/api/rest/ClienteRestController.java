package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.ClienteDto;
import br.com.mercadoon.api.entity.Cliente;
import br.com.mercadoon.api.service.ClienteService;
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
@RequestMapping(value = "/api/cliente", produces = { "application/json" })
@Tag(name = "Cliente")
public class ClienteRestController {
    private ClienteService clienteService;

    @Autowired
    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Lista todos os clientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes listados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    @GetMapping
    public List<ClienteDto> listar() {
        return clienteService.listar();
    }

    @Operation(summary = "Busca cliente por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ClienteDto buscar(@PathVariable Long id) {
        return clienteService.buscar(id);
    }

    @Operation(summary = "Cadastra cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PostMapping(value = "/{usuarioId}", consumes = { "application/json" })
    public ResponseEntity<ClienteDto> add(@Valid @RequestBody Cliente cliente, @PathVariable Long usuarioId) {
        return new ResponseEntity<>(clienteService.add(cliente, usuarioId), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping(value = "/{id}", consumes = { "application/json" })
    public ClienteDto atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return clienteService.atualizar(id, cliente);
    }

    @Operation(summary = "Deleta cliente", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta todos os clientes", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Clientes deletados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        clienteService.deletarTodos();
        return ResponseEntity.noContent().build();
    }
}
