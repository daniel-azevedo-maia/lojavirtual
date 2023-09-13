package com.danielazevedo.lojavirtual.domain.service;


import com.danielazevedo.lojavirtual.domain.model.Cliente;
import com.danielazevedo.lojavirtual.domain.model.ItemPedido;
import com.danielazevedo.lojavirtual.domain.model.Pedido;
import com.danielazevedo.lojavirtual.domain.repository.ClienteRepository;
import com.danielazevedo.lojavirtual.domain.repository.PedidoRepository;
import com.danielazevedo.lojavirtual.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;

    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long pedidoId) {
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        return pedido;
    }

    public Pedido cadastrar(Pedido pedido) {

        Optional<Cliente> cliente = clienteRepository.findById(pedido.getCliente().getId());

        pedido.setCliente(cliente.get());

        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedido item : pedido.getItens()) {
            item.setProduto(produtoRepository.findById(item.getProduto().getId()).orElse(null));
            item.setPedido(pedido);
        }

        return pedidoRepository.save(pedido);
    }

    public void remover(Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }


}
