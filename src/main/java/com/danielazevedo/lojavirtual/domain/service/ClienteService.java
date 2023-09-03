package com.danielazevedo.lojavirtual.domain.service;

import com.danielazevedo.lojavirtual.domain.model.Cidade;
import com.danielazevedo.lojavirtual.domain.model.Cliente;
import com.danielazevedo.lojavirtual.domain.model.Endereco;
import com.danielazevedo.lojavirtual.domain.model.Estado;
import com.danielazevedo.lojavirtual.domain.repository.CidadeRepository;
import com.danielazevedo.lojavirtual.domain.repository.ClienteRepository;
import com.danielazevedo.lojavirtual.domain.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private CidadeRepository cidadeRepository;

    public ClienteService(ClienteRepository clienteRepository, CidadeRepository cidadeRepository) {
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
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
