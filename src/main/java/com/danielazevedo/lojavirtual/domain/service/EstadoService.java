package com.danielazevedo.lojavirtual.domain.service;

import com.danielazevedo.lojavirtual.domain.model.Estado;
import com.danielazevedo.lojavirtual.domain.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    private EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscarPorId(Long estadoId) {
        Optional<Estado> estado = estadoRepository.findById(estadoId);
        return estado;
    }

    public Estado cadastrar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover(Long estadoId) {
        estadoRepository.deleteById(estadoId);
    }

}
