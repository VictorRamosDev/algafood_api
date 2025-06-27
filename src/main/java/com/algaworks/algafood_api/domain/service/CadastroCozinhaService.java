package com.algaworks.algafood_api.domain.service;

import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;

    @Autowired
    public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void remover(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch(EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Cozinha com código %d não foi encontrada.", cozinhaId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha com código %d não pode ser removida, pois está em uso.", cozinhaId)
            );
        }
    }

}
