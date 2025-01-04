package br.com.mercadoon.api.dto;

import br.com.mercadoon.api.enumeration.StatusCompra;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class CompraDto {
    private Long id;
    private ClienteDto cliente;
    private List<ProdutoCompraDto> produtos;
    private CartaoCompraDto cartao;
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
