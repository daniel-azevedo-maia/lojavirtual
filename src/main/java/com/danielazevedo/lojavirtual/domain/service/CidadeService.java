package com.danielazevedo.lojavirtual.domain.service;

import com.danielazevedo.lojavirtual.domain.model.Cidade;
import com.danielazevedo.lojavirtual.domain.model.Estado;
import com.danielazevedo.lojavirtual.domain.repository.CidadeRepository;
import com.danielazevedo.lojavirtual.domain.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;
    private EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository,
                         EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> buscarPorId(Long cidadeId) {
        Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
        return cidade;
    }

    public Cidade cadastrar(Cidade cidade) {

        Long estadoId = cidade.getEstado().getId();
        Optional<Estado> estado = estadoRepository.findById(estadoId);

        cidade.setEstado(estado.get());

        return cidadeRepository.save(cidade);
    }

    public void remover(Long cidadeId) {
        cidadeRepository.deleteById(cidadeId);
    }

}
