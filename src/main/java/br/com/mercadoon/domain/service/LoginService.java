package br.com.mercadoon.domain.service;

import br.com.mercadoon.api.dto.ClienteDto;
import br.com.mercadoon.api.entity.Cliente;
import br.com.mercadoon.api.exception.LoginException;
import br.com.mercadoon.api.repository.ClienteRepository;
import br.com.mercadoon.util.Validador;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class LoginService {
    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper;

    public LoginService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public ClienteDto fazerLogin(String email, String senha) throws LoginException {
        if(Validador.emailValido(email)) {
            if(senha != null && senha != "") {
                Cliente cliente = clienteRepository.findByEmail(email);

                if(cliente != null && cliente.getSenha().equals(senha)) {
                    return modelMapper.map(cliente, ClienteDto.class);
                } else {
                    throw new LoginException("Senha errada");
                }
            } else {
                throw new LoginException("Senha vazia");
            }
        } else {
            throw new LoginException("E-mail inválido");
        }
    }

}
