package com.algaworks.algafood_api.domain.repository;

import com.algaworks.algafood_api.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

    List<Cozinha> findByNome(String nome);

}
