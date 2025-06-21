package com.algaworks.algafood_api.domain.service;

import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Cidade;
import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.CidadeRepository;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CadastroCidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade atualizar(Long cidadeId, Cidade request) {
        Cidade cidade = cidadeRepository.buscar(cidadeId);

        if (cidade == null) {
            throw new EntidadeNaoEncontradaException(String.format("A cidade de código %d não foi encontrada.", cidadeId));
        }

        BeanUtils.copyProperties(request, cidade, "id");

        return cidadeRepository.salvar(cidade);
    }

    public Cidade salvar(Cidade request) {
        Estado estado = estadoRepository.buscar(request.getEstado().getId());
        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("A cidade está fazendo referência a um Estado de código %d que não foi encontrado no sistema.", request.getEstado().getId())
            );
        }
        request.setEstado(estado);

        return cidadeRepository.salvar(request);
    }

    public void remover(Long cidadeId) {
        try {
            cidadeRepository.remover(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("A cidade de código %d não foi encontrada.", cidadeId));
        }
    }
}
