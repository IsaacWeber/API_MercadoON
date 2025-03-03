package br.com.mercadoon.api.service;

import br.com.mercadoon.api.dto.ClienteDto;
import br.com.mercadoon.api.entity.Cliente;
import br.com.mercadoon.api.entity.Usuario;
import br.com.mercadoon.api.exception.ClienteNotFoundException;
import br.com.mercadoon.api.exception.UsuarioException;
import br.com.mercadoon.api.exception.UsuarioNotFoundException;
import br.com.mercadoon.api.repository.ClienteRepository;
import br.com.mercadoon.api.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;
    private UsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper, UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ClienteDto> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, ClienteDto.class))
                .collect(Collectors.toList());
    }

    public ClienteDto buscar(Long id) {
        return modelMapper.map(clienteRepository
                        .findById(id)
                        .orElseThrow(() -> new ClienteNotFoundException("Usuário não encontrado para id = " + id)),
                ClienteDto.class);
    }

    public ClienteDto add(Cliente cliente, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                                            .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado para id = " + usuarioId));

        if(usuario.getCliente() != null) {
            throw new UsuarioException("Usuário de id = " + usuarioId + " já possui cliente.");
        }

        cliente.setId(null);
        cliente.setMembroDesde(new Date(System.currentTimeMillis()));

        if(cliente.getCartoes() != null)
            cliente.getCartoes().forEach(c -> c.setCliente(cliente));

        cliente.setUsuario(usuario);
        usuario.setCliente(cliente);

        return modelMapper.map(clienteRepository.save(cliente), ClienteDto.class);
    }

    public ClienteDto atualizar(Long id, Cliente novoCliente) {
        Cliente bdCliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente não econtrado para id = " + id));

        novoCliente.setId(bdCliente.getId());
        novoCliente.setMembroDesde(bdCliente.getMembroDesde());
        novoCliente.setCartoes(null);

        return modelMapper.map(clienteRepository.save(novoCliente), ClienteDto.class);
    }

    public void deletar(Long id) {
        clienteRepository.delete(
                clienteRepository.findById(id)
                        .orElseThrow(() ->
                                new ClienteNotFoundException("Cliente não econtrado para id = " + id))); // Pesquisa somente para verificar a existência do cliente

    }

    public void deletarTodos() {
        clienteRepository.deleteAll();
    }
}

