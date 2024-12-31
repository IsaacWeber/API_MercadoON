package br.com.mercadoon.rest;

import br.com.mercadoon.entity.Arquivo;
import br.com.mercadoon.service.ArquivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/arquivo")
public class ArquivoRestController {
    private ArquivoService arquivoService;

    public ArquivoRestController(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }
    // CADASTRE A TABELA NO FLYWAY
    @PostMapping("/upload")
    public Arquivo upload(@RequestParam("arquivo")MultipartFile arquivoMultipart) {
        return arquivoService.add(arquivoMultipart);
    }

}
