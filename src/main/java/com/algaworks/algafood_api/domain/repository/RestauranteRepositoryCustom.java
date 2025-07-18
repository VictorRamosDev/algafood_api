package com.algaworks.algafood_api.domain.repository;

import com.algaworks.algafood_api.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryCustom {

    List<Restaurante> findByJpql(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByCriteria(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByCriteriaOptimized(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

}
