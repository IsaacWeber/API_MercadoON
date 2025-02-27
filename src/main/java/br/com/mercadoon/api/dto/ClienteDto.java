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
    private Date membroDesde;
    private UsuarioResumoDto usuario;
}
