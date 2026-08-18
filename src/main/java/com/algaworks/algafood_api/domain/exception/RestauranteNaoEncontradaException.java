package com.algaworks.algafood_api.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final Long serialVersion = 1L;

    public RestauranteNaoEncontradaException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradaException(Long restauranteId) {
        super(String.format("O restaurante de código %d não foi encontrado.", restauranteId));
    }
}
