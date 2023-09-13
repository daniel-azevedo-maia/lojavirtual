package com.danielazevedo.lojavirtual.domain.repository;

import com.danielazevedo.lojavirtual.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
