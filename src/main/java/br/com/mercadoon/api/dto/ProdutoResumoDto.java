package br.com.mercadoon.api.dto;

import br.com.mercadoon.api.enumeration.CategoriaProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResumoDto {
    private Long id;
    private String nome;
    private CategoriaProduto categoria;
    private Double preco;
}
