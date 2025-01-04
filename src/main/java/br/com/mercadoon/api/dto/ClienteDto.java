package br.com.mercadoon.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ClienteDto {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String endereco;
    private Date membroDesde;
}
