package com.danielazevedo.lojavirtual.domain.service;

import com.danielazevedo.lojavirtual.domain.model.Produto;
import com.danielazevedo.lojavirtual.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long produtoId) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);
        return produto;
    }

    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void remover(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }


}
