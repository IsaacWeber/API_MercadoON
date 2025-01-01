package br.com.mercadoon.api.dto;

import br.com.mercadoon.api.enumeration.CategoriaProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {
    private String nome;
    private String marca;
    private CategoriaProduto categoria;
    private String modelo;
    private String cor;
    private String descricao;
    private String descricaoTecnica;
    private Double preco;
}
