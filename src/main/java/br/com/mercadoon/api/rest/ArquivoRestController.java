package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.entity.Arquivo;
import br.com.mercadoon.api.service.ArquivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/arquivo")
public class ArquivoRestController {
    private ArquivoService arquivoService;

    public ArquivoRestController(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

    @GetMapping("/download/{id}")
    public Arquivo download(@PathVariable Long id) {
        return arquivoService.buscar(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<Arquivo> upload(@RequestParam(value = "arquivo", required = false)MultipartFile arquivoMultipart) {
        return new ResponseEntity<>(arquivoService.add(arquivoMultipart), HttpStatus.CREATED);
    }

    @PostMapping("/upload/{produtoId}")
    public void upload(@PathVariable Long produtoId, @RequestParam(value = "arquivos", required = false)List<MultipartFile> arquivosMultipart) {
        arquivoService.add(produtoId, arquivosMultipart);
    }

}
