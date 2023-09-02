package com.danielazevedo.lojavirtual.controlller;

import com.danielazevedo.lojavirtual.domain.model.Estado;
import com.danielazevedo.lojavirtual.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {

        Optional<Estado> estado = estadoService.buscarPorId(estadoId);

        if(estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Estado> cadastrar(@RequestBody Estado estado) {

        Estado estadoSalvo = estadoService.cadastrar(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);

    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@RequestBody Estado estado,
                                            @PathVariable Long estadoId) {

        Optional<Estado> estadoEncontrado = estadoService.buscarPorId(estadoId);

        if(estadoEncontrado.isPresent()) {

            BeanUtils.copyProperties(estado, estadoEncontrado.get(), "id");
            estadoService.cadastrar(estadoEncontrado.get());

            return ResponseEntity.ok(estadoEncontrado.get());

        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Estado> remover(@PathVariable Long estadoId) {

        Optional<Estado> estado = estadoService.buscarPorId(estadoId);

        if (estado.isPresent()) {
            estadoService.remover(estado.get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.notFound().build();

    }

}
