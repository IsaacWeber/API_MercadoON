package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.ClienteDto;
import br.com.mercadoon.api.entity.Cliente;
import br.com.mercadoon.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController {
    private ClienteService clienteService;

    @Autowired
    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDto> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteDto buscar(@PathVariable Long id) {
        return clienteService.buscar(id);
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<ClienteDto> add(@Valid @RequestBody Cliente cliente, @PathVariable Long usuarioId) {
        return new ResponseEntity<>(clienteService.add(cliente, usuarioId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ClienteDto atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return clienteService.atualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        clienteService.deletarTodos();
        return ResponseEntity.noContent().build();
    }
}
