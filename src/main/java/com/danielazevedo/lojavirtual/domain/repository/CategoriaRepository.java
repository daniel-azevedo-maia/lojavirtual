package com.danielazevedo.lojavirtual.domain.repository;

import com.danielazevedo.lojavirtual.domain.model.Categoria;
import com.danielazevedo.lojavirtual.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}
