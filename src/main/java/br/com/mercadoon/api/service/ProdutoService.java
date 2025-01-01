package br.com.mercadoon.api.service;

import br.com.mercadoon.api.dto.ProdutoDto;
import br.com.mercadoon.api.entity.Arquivo;
import br.com.mercadoon.api.entity.Produto;
import br.com.mercadoon.api.exception.ProdutoNotFoundException;
import br.com.mercadoon.api.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private ModelMapper modelMapper;
    private ArquivoService arquivoService;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ModelMapper modelMapper, ArquivoService arquivoService) {
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
        this.arquivoService = arquivoService;
    }

    public List<ProdutoDto> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProdutoDto.class))
                .collect(Collectors.toList());
    }

    public ProdutoDto add(Produto produto, List<MultipartFile> arquivosMultipart) {
        produto.setId(null);

        if(arquivosMultipart != null) {
            // Pega somente arquivos de imagens
            List<Arquivo> imagens =
                    arquivosMultipart.stream()
                            .filter(a -> a != null)
                            .filter(a -> a.getContentType().startsWith("image/"))
                            .map(a -> {
                                try {
                                    return new Arquivo(StringUtils.cleanPath(
                                            a.getOriginalFilename()),
                                            a.getContentType(),
                                            a.getBytes());
                                } catch (IOException e) {
                                    throw new RuntimeException("Erro adicionando arquivo! Verifique os dados.");
                                }
                            })
                            .collect(Collectors.toList());

            produto.setImagens(new ArrayList<>(imagens));

            for (Arquivo imagem : imagens) {
                imagem.setProdutos(new ArrayList<>());
                imagem.getProdutos().add(produto);
            }
        }

        return modelMapper.map(produtoRepository.save(produto), ProdutoDto.class);
    }

    public ProdutoDto buscar(Long id) {
        return modelMapper.map(
                produtoRepository.findById(id)
                        .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado para id = " + id)),
                ProdutoDto.class);
    }

    public ProdutoDto atualizar(Long id, Produto produto) {
        produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado para id = " + id));
        produto.setId(id);
        produtoRepository.save(produto);
        return modelMapper.map(produto, ProdutoDto.class);
    }

    public void deletar(Long id) {
        produtoRepository.delete(
                produtoRepository.findById(id)
                        .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado para id = " + id)));
    }

    public void deletarTodos() {
        produtoRepository.deleteAll();
    }

    public boolean existe(Long id) {
        return produtoRepository.existsById(id);
    }
}
