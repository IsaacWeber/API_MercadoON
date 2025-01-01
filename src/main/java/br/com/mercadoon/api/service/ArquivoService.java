package br.com.mercadoon.api.service;

import br.com.mercadoon.api.entity.Arquivo;
import br.com.mercadoon.api.exception.ArquivoNotFoundException;
import br.com.mercadoon.api.repository.ArquivoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArquivoService {
    private ArquivoRepository arquivoRepository;

    public ArquivoService(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    public Arquivo add(MultipartFile arquivoMultipart) {
        if(arquivoMultipart == null) throw new RuntimeException("Arquivo não pode ser nulo.");

        try {
            byte[] data = arquivoMultipart.getBytes();
            if(data.length == 0) throw new RuntimeException("Arquivo não pode estar vazio.");

            Arquivo arquivo = new Arquivo(StringUtils.cleanPath(arquivoMultipart.getOriginalFilename()),
                    arquivoMultipart.getContentType(), arquivoMultipart.getBytes());

            return arquivoRepository.save(arquivo);
        } catch (IOException e) {
            throw new RuntimeException("Erro adicionando arquivo! Verifique os dados.");
        }
    }

    public void addAll(List<Arquivo> arquivos) {
        arquivos.forEach(a -> arquivoRepository.save(a));
    }

    public Arquivo buscar(Long id) {
        return arquivoRepository.findById(id)
                .orElseThrow(() -> new ArquivoNotFoundException("Arquivo não encontrado para id = " + id));
    }
}


