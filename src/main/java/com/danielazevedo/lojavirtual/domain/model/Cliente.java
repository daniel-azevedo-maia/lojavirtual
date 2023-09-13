package com.danielazevedo.lojavirtual.domain.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String cpf;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataNascimento;

    private LocalDateTime dataCadastro;

}