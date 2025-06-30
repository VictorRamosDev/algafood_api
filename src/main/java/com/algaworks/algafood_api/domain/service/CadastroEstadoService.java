package com.algaworks.algafood_api.domain.service;

import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroEstadoService {

    private final EstadoRepository estadoRepository;

    @Autowired
    public CadastroEstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado atualizar(Long estadoId, Estado estado) {
        Optional<Estado> estadoEntityOpt = estadoRepository.findById(estadoId);
        if (estadoEntityOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("O estado de código %d não foi encontrado no sistema.", estadoId));
        }

        BeanUtils.copyProperties(estado, estadoEntityOpt.get(), "id");
        return estadoRepository.save(estadoEntityOpt.get());
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch(EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Estado de código %d não encontrado.", estadoId));
        } catch(DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("O Estado com código %d está sendo utilizado no sistema.", estadoId));
        }
    }
}
