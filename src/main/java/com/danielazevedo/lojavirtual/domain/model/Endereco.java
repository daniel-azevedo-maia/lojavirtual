package com.danielazevedo.lojavirtual.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    private String cep;

}
