package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.ProdutoDto;
import br.com.mercadoon.api.entity.Arquivo;
import br.com.mercadoon.api.entity.Produto;
import br.com.mercadoon.api.service.ArquivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Arquivo download(@PathVariable Long id) {
        return arquivoService.buscar(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<Arquivo> upload(@RequestParam(value = "arquivo", required = false)MultipartFile arquivoMultipart) {
        return new ResponseEntity<>(arquivoService.add(arquivoMultipart), HttpStatus.CREATED);
    }

    @PostMapping("/upload/{produtoId}")
    public Object upload(@PathVariable Long produtoId,
                         @RequestParam(value = "arquivos", required = false) List<MultipartFile> arquivosMultipart,
                         @RequestParam(value = "from_model", required = false) Integer fromModel) {

        ProdutoDto produtoDto = arquivoService.add(produtoId, arquivosMultipart);
        if(fromModel == 1) {
        return new ModelAndView("redirect:/produto/exibir/" + produtoDto.getId());
        }

        return new ResponseEntity<>(produtoDto, HttpStatus.CREATED);
    }

}
