package com.danielazevedo.lojavirtual.controlller;


import com.danielazevedo.lojavirtual.domain.model.Produto;
import com.danielazevedo.lojavirtual.domain.service.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {

        Produto produtoSalvo = produtoService.cadastrar(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> buscar(@PathVariable Long produtoId) {

        Optional<Produto> produto = produtoService.buscarPorId(produtoId);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long produtoId,
                                             @RequestBody Produto produto) {

        Optional<Produto> produtoEncontrado = produtoService.buscarPorId(produtoId);

        if (produtoEncontrado.isPresent()) {
            BeanUtils.copyProperties(produto, produtoEncontrado.get(), "id", "dataCadastro");

            produtoService.cadastrar(produtoEncontrado.get());

            return ResponseEntity.ok(produtoEncontrado.get());
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Produto> remover(@PathVariable Long produtoId) {

        Optional<Produto> produto = produtoService.buscarPorId(produtoId);

        if (produto.isPresent()) {
            produtoService.remover(produto.get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.notFound().build();

    }

}
