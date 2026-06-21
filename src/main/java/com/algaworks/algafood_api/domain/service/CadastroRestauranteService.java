package com.algaworks.algafood_api.domain.service;

import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    @Autowired
    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha de código %d.", cozinhaId)));
        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Optional<Restaurante> restauranteEntityOpt = restauranteRepository.findById(restauranteId);

        if (restauranteEntityOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("O restaurante de código %d não foi encontrado.", restauranteId));
        }

        Optional<Cozinha> cozinhaOpt = cozinhaRepository.findById(restaurante.getCozinha().getId());
        if (cozinhaOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("A cozinha de código %d não existe no sistema.", restaurante.getCozinha().getId()));
        }

        BeanUtils.copyProperties(restaurante, restauranteEntityOpt.get(), "id", "formasPagamento");
        return restauranteRepository.save(restauranteEntityOpt.get());
    }

    public void remover(Long restauranteId) {
        try {
            restauranteRepository.deleteById(restauranteId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("O restaurante de código %d não foi encontrado.", restauranteId));
        }
    }
}
