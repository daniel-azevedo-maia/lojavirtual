package com.danielazevedo.lojavirtual.controlller;


import com.danielazevedo.lojavirtual.domain.model.Pedido;
import com.danielazevedo.lojavirtual.domain.service.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido) {

        Pedido pedidoSalvo = pedidoService.cadastrar(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long pedidoId) {

        Optional<Pedido> pedido = pedidoService.buscarPorId(pedidoId);

        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long pedidoId,
                                             @RequestBody Pedido pedido) {

        Optional<Pedido> pedidoEncontrado = pedidoService.buscarPorId(pedidoId);

        if (pedidoEncontrado.isPresent()) {
            BeanUtils.copyProperties(pedido, pedidoEncontrado.get(), "id", "dataCadastro");

            pedidoService.cadastrar(pedidoEncontrado.get());

            return ResponseEntity.ok(pedidoEncontrado.get());
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Pedido> remover(@PathVariable Long pedidoId) {

        Optional<Pedido> pedido = pedidoService.buscarPorId(pedidoId);

        if (pedido.isPresent()) {
            pedidoService.remover(pedido.get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.notFound().build();

    }

}
