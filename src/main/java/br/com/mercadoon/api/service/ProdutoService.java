package br.com.mercadoon.api.service;

import br.com.mercadoon.api.dto.ProdutoDto;
import br.com.mercadoon.api.entity.Arquivo;
import br.com.mercadoon.api.entity.Cliente;
import br.com.mercadoon.api.entity.Produto;
import br.com.mercadoon.api.exception.ClienteNotFoundException;
import br.com.mercadoon.api.exception.ProdutoNotFoundException;
import br.com.mercadoon.api.repository.ClienteRepository;
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
    private ClienteRepository clienteRepository;

    public ProdutoService(ArquivoService arquivoService, ClienteRepository clienteRepository, ModelMapper modelMapper, ProdutoRepository produtoRepository) {
        this.arquivoService = arquivoService;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDto> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProdutoDto.class))
                .collect(Collectors.toList());
    }

    public ProdutoDto add(Long clienteId, Produto produto) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado para id = " + clienteId));

        produto.setId(null);
        produto.setCliente(cliente);
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

    public List<ProdutoDto> pegarPorClienteId(Long id) {
        return produtoRepository.findAllByClienteId(id)
                                .stream()
                                .map(p -> modelMapper.map(p, ProdutoDto.class))
                                .collect(Collectors.toList());
    }
}
