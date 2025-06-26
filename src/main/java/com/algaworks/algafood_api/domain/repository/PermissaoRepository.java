package com.algaworks.algafood_api.domain.repository;

import com.algaworks.algafood_api.domain.model.Permissao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissaoRepository {

    List<Permissao> listar();

    Permissao buscar(Long id);

    Permissao salvar(Permissao permissao);

    void remover(Permissao permissao);

}
