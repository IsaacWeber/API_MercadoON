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
    private ClienteResumoDto cliente;
    private CartaoResumoDto cartao;
    private List<ProdutoResumoDto> produtos;
    private Date realizacao;
    private Date previsaoEntrega;
    private StatusCompra status;
    private Date entrega;
}
