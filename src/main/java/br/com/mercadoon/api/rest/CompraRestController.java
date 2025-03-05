package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.CompraDto;
import br.com.mercadoon.api.requestmodel.CompraRequestModel;
import br.com.mercadoon.api.service.CompraService;
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
@RequestMapping(value = "/api/compra", produces = { "application/json" })
@Tag(name = "Compra")
public class CompraRestController {

    private CompraService compraService;

    @Autowired
    public CompraRestController(CompraService compraService) {
        this.compraService = compraService;
    }

    @Operation(summary = "Lista todas as compras", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compras listadas"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    @GetMapping
    public List<CompraDto> listar() {
        return compraService.listar();
    }

    @Operation(summary = "Busca compra por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra encontrada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada")
    })
    @GetMapping("/{id}")
    public CompraDto buscar(@PathVariable Long id) {
        return compraService.buscar(id);
    }

    @Operation(summary = "Busca compras por cliente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compras listadas"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/cliente/{clienteId}")
    public List<CompraDto> buscarPorClienteId(@PathVariable Long clienteId) {
        return compraService.buscarPorClienteId(clienteId);
    }

    @Operation(summary = "Cadastra compra", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra cadastrada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    })
    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<CompraDto> add(@Valid @RequestBody CompraRequestModel compraRequestModel){
        return new ResponseEntity<>(compraService.add(compraRequestModel), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza compra", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra atualizada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada")
    })
    @PutMapping(value = "/{id}", consumes = { "application/json" })
    public CompraDto atualizar(@PathVariable Long id, @Valid @RequestBody CompraRequestModel compraRequestModel) {
        return compraService.atualizar(id, compraRequestModel);
    }

    @Operation(summary = "Deleta compra", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Compra deletada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        compraService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta todas as compras", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Compras deletados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        compraService.deletarTodos();
        return ResponseEntity.noContent().build();
    }
}
