package com.danielazevedo.lojavirtual.domain.service;

import com.danielazevedo.lojavirtual.domain.model.Cidade;
import com.danielazevedo.lojavirtual.domain.model.Cliente;
import com.danielazevedo.lojavirtual.domain.model.Endereco;
import com.danielazevedo.lojavirtual.domain.model.Estado;
import com.danielazevedo.lojavirtual.domain.repository.CidadeRepository;
import com.danielazevedo.lojavirtual.domain.repository.ClienteRepository;
import com.danielazevedo.lojavirtual.domain.repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private EstadoRepository estadoRepository;
    private CidadeRepository cidadeRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          EstadoRepository estadoRepository,
                          CidadeRepository cidadeRepository) {
        this.clienteRepository = clienteRepository;
        this.estadoRepository = estadoRepository;
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


        System.out.println(" ------------- DADOS DO CLIENTE ----------------------");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("RG: " + cliente.getRg());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Rua: " + cliente.getEndereco().getLogradouro());
        System.out.println("Número: " + cliente.getEndereco().getNumero());
        System.out.println("Bairro: " + cliente.getEndereco().getBairro());
        System.out.println("CEP: " + cliente.getEndereco().getCep());
        System.out.println("ID da cidade: " + cliente.getEndereco().getCidade().getId());
        System.out.println("Data de cadastro: " + cliente.getDataCadastro());
        System.out.println("Data de nascimento: " + cliente.getDataNascimento());
        System.out.println(" ----------------------------------------------");

        Long cidadeId = cliente.getEndereco().getCidade().getId();

        Cidade cidade = cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));
        cliente.getEndereco().setCidade(cidade);

        return clienteRepository.save(cliente);
    }

    public void remover(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }


}
