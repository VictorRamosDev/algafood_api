package com.algaworks.algafood_api.domain.service;

import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Cidade;
import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.CidadeRepository;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CadastroCidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade atualizar(Long cidadeId, Cidade request) {
        Optional<Cidade> cidadeOpt = cidadeRepository.findById(cidadeId);

        if (cidadeOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("A cidade de código %d não foi encontrada.", cidadeId));
        }

        BeanUtils.copyProperties(request, cidadeOpt.get(), "id");

        return cidadeRepository.save(cidadeOpt.get());
    }

    public Cidade salvar(Cidade request) {
        Optional<Estado> estadoOpt = estadoRepository.findById(request.getEstado().getId());
        if (estadoOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("A cidade está fazendo referência a um Estado de código %d que não foi encontrado no sistema.", request.getEstado().getId())
            );
        }
        request.setEstado(estadoOpt.get());

        return cidadeRepository.save(request);
    }

    public void remover(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("A cidade de código %d não foi encontrada.", cidadeId));
        }
    }
}
