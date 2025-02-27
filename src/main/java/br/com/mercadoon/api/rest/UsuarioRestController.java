package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.UsuarioDto;
import br.com.mercadoon.api.entity.Usuario;
import br.com.mercadoon.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar() {
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

}
