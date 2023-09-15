package com.danielazevedo.lojavirtual.domain.service;

import com.danielazevedo.lojavirtual.domain.model.Categoria;
import com.danielazevedo.lojavirtual.domain.model.Produto;
import com.danielazevedo.lojavirtual.domain.repository.CategoriaRepository;
import com.danielazevedo.lojavirtual.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long produtoId) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);
        return produto;
    }

    public Produto cadastrar(Produto produto) {

        Long categoriaId = produto.getCategoria().getId();

        Optional<Categoria> categoria = Optional.ofNullable(categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada")));

        if(categoria.isPresent()) {
            produto.setCategoria(categoria.get());
        } else {
            throw new EntityNotFoundException("Categoria não encontrada!");
        }

        return produtoRepository.save(produto);
    }

    public void remover(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }


}
