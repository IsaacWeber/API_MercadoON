package br.com.mercadoon.api.dto;

import br.com.mercadoon.api.entity.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String email;
}
