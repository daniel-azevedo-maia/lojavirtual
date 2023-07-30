package com.danielazevedo.lojavirtual.repository;

import com.danielazevedo.lojavirtual.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
