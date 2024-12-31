package br.com.mercadoon.service;

import br.com.mercadoon.entity.Arquivo;
import br.com.mercadoon.repository.ArquivoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArquivoService {
    private ArquivoRepository arquivoRepository;

    public ArquivoService(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    public Arquivo add(MultipartFile arquivoMultipart) {
        String nome = StringUtils.cleanPath(arquivoMultipart.getOriginalFilename());

        try {
            Arquivo arquivo = new Arquivo(nome, arquivoMultipart.getContentType(), arquivoMultipart.getBytes());
            return arquivoRepository.save(arquivo);
        } catch (IOException e) {
            throw new RuntimeException("Erro adicionando arquivo!");
        }
    }
}


