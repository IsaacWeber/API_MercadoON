package br.com.mercadoon.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResumoDto {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
}
