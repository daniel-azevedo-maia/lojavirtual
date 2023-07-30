package com.danielazevedo.lojavirtual.service;

import com.danielazevedo.lojavirtual.model.cliente.Cliente;
import com.danielazevedo.lojavirtual.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        return cliente;
    }

    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void remover(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }


}
