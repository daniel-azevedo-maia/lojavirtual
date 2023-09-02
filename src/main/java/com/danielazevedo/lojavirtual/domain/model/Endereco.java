package com.danielazevedo.lojavirtual.domain.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Endereco {

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @Column(nullable = false)
    private String cep;

}
