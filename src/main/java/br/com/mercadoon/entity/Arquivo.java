package br.com.mercadoon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="arquivo")
public class Arquivo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nome")
    private String tipo;

    @Lob
    private byte[] data;

    public Arquivo(String nome, String tipo, byte[] data) {
        this.data = data;
        this.nome = nome;
        this.tipo = tipo;
    }
}
