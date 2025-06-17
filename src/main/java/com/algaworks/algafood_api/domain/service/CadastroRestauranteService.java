package com.algaworks.algafood_api.domain.service;

import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Autowired
    public CadastroRestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

}
