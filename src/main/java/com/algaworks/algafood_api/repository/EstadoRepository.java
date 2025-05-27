package com.algaworks.algafood_api.repository;

import com.algaworks.algafood_api.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado estado);

    void remover(Estado estado);

}
