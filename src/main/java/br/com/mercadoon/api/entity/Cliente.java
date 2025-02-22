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

    @Column(name="cpf")
    @NotEmpty(message = "CPF não pode estar vazio.")
    private String cpf;

    @Column(name="email")
    @NotEmpty(message = "E-mail não pode estar vazio.")
    private String email;

    @Column(name="endereco")
    @NotEmpty(message = "Endereço não pode estar vazio.")
    private String endereco;

    @Column(name = "senha")
    @NotEmpty(message = "Senha não pode estar vazia.")
    private String senha;

    @Column(name="membro_desde")
    private Date membroDesde;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cartao> cartoes;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Compra> compras;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Produto> produtos;
}
