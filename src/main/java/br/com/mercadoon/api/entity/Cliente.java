package br.com.mercadoon.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    @NotEmpty(message = "Nome não pode estar vazio.")
    private String nome;

    @Column(name="sobrenome")
    @NotEmpty(message = "Sobrenome não pode estar vazio.")
    private String sobrenome;

    @Column(name="endereco")
    @NotEmpty(message = "Endereço não pode estar vazio.")
    private String endereco;

    @Column(name="membro_desde")
    private Date membroDesde;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cartao> cartoes;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Compra> compras;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @OneToOne(mappedBy = "cliente", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Usuario usuario;
}
