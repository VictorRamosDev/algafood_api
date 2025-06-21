package com.algaworks.algafood_api.infrastructure.repository;

import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
        return query.getResultList();
    }

    @Override
    public Estado buscar(Long id) {

        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Long estadoId) {
        Estado estado1 = buscar(estadoId);

        if (estado1 == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(estado1);
    }
}
