package com.danielazevedo.lojavirtual.controlller;


import com.danielazevedo.lojavirtual.domain.model.Cidade;
import com.danielazevedo.lojavirtual.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {

        Optional<Cidade> cidade = cidadeService.buscarPorId(cidadeId);

        if(cidade.isPresent()) {
            return ResponseEntity.ok(cidade.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Cidade> cadastrar(@RequestBody Cidade cidade) {

        Cidade cidadeSalva = cidadeService.cadastrar(cidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);

    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade,
                                            @PathVariable Long cidadeId) {

        Optional<Cidade> cidadeEncontrada = cidadeService.buscarPorId(cidadeId);

        if(cidadeEncontrada.isPresent()) {

            BeanUtils.copyProperties(cidade, cidadeEncontrada.get(), "id");
            cidadeService.cadastrar(cidadeEncontrada.get());

            return ResponseEntity.ok(cidadeEncontrada.get());

        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {

        Optional<Cidade> cidade = cidadeService.buscarPorId(cidadeId);

        if (cidade.isPresent()) {
            cidadeService.remover(cidade.get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.notFound().build();

    }

}
