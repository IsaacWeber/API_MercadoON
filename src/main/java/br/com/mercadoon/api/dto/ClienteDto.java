package br.com.mercadoon.api.dto;

import br.com.mercadoon.api.entity.Arquivo;
import br.com.mercadoon.api.entity.Compra;
import br.com.mercadoon.api.entity.Produto;
import br.com.mercadoon.api.enumeration.CategoriaProduto;
import br.com.mercadoon.api.enumeration.StatusCompra;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ClienteDto {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String endereco;
    private List<ProdutoDto> produtos;
    private List<CompraDto> compras;
    private Date membroDesde;
/*
    @Getter
    @Setter
    static class ProdutoDto {
        private Long id;
        private String nome;
        private String marca;
        private CategoriaProduto categoria;
        private String modelo;
        private String cor;
        private String descricao;
        private String descricaoTecnica;
        private Double preco;
        private List<Arquivo> imagens;
    }

    @Getter
    @Setter
    static class CompraDto {
        private Long id;
        private ClienteDto cliente;
        private List<br.com.mercadoon.api.dto.CompraDto.ProdutoCompraDto> produtos;
        private br.com.mercadoon.api.dto.CompraDto.CartaoCompraDto cartao;
        private Date realizacao;
        private Date previsaoEntrega;
        private StatusCompra status;
        private Date entrega;

        @Getter
        @Setter
        static class ProdutoCompraDto {
            private String nome;
            private String marca;
        }

        @Getter
        @Setter
        static class CartaoCompraDto {
            private String numero;
            private String bandeira;
        }

    }

 */
}
