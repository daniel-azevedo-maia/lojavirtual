package com.danielazevedo.lojavirtual.controlller;


import com.danielazevedo.lojavirtual.model.cliente.Cliente;
import com.danielazevedo.lojavirtual.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {

        Optional<Cliente> cliente = clienteService.buscarPorId(clienteId);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {

        cliente.setDataCadastro(LocalDateTime.now());

        Cliente clienteSalvo = clienteService.cadastrar(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
                                             @RequestBody Cliente cliente) {

        Optional<Cliente> clienteEncontrado = clienteService.buscarPorId(clienteId);

        if (clienteEncontrado.isPresent()) {
            BeanUtils.copyProperties(cliente, clienteEncontrado.get(), "id", "dataCadastro");

            clienteService.cadastrar(clienteEncontrado.get());

            return ResponseEntity.ok(clienteEncontrado.get());
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Cliente> remover(@PathVariable Long clienteId) {

        Optional<Cliente> cliente = clienteService.buscarPorId(clienteId);

        if (cliente.isPresent()) {
            clienteService.remover(cliente.get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.notFound().build();

    }

}
