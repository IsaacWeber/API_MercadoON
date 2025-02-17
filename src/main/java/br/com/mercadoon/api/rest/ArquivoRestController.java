package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.ArquivoDto;
import br.com.mercadoon.api.dto.ProdutoDto;
import br.com.mercadoon.api.entity.Arquivo;
import br.com.mercadoon.api.entity.Produto;
import br.com.mercadoon.api.service.ArquivoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/arquivo")
public class ArquivoRestController {
    private ArquivoService arquivoService;

    public ArquivoRestController(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

    @GetMapping("/download/{id}")
    public ArquivoDto download(@PathVariable Long id) {
        return arquivoService.buscar(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<ArquivoDto> upload(@RequestParam(value = "arquivo", required = false)MultipartFile arquivoMultipart) {
        return new ResponseEntity<>(arquivoService.add(arquivoMultipart), HttpStatus.CREATED);
    }
}
