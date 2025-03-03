package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.UsuarioDto;
import br.com.mercadoon.api.entity.Usuario;
import br.com.mercadoon.api.service.JwtService;
import br.com.mercadoon.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    private UsuarioService usuarioService;
    private JwtService jwtService;

    @Autowired
    public UsuarioRestController(JwtService jwtService, UsuarioService usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDto> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public UsuarioDto buscar(@PathVariable Long id) {
        return usuarioService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> add(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.add(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public UsuarioDto atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        usuarioService.deletarTodos();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/token")
    public String generateToken() {
        return jwtService.generateToken();
    }
}
