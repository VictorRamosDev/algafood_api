package com.algaworks.algafood_api.domain.service;

import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha de código %d.", cozinhaId));
        }

        return restauranteRepository.salvar(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Restaurante restauranteEntity = restauranteRepository.buscar(restauranteId);

        if (restauranteEntity == null) {
            throw new EntidadeNaoEncontradaException(String.format("O restaurante de código %d não foi encontrado.", restauranteId));
        }

        Cozinha cozinha = cozinhaRepository.buscar(restaurante.getCozinha().getId());
        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("A cozinha de código %d não existe no sistema.", restaurante.getCozinha().getId()));
        }

        BeanUtils.copyProperties(restaurante, restauranteEntity, "id");
        return restauranteRepository.salvar(restauranteEntity);
    }
}
