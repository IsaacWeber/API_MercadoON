package br.com.mercadoon.api.dto;

import br.com.mercadoon.api.enumeration.FuncaoCartao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoResumoDto {
    private Long id;
    private String nomeUsuario;
    private String numero;
    private FuncaoCartao funcao;
}
